package org.example.services.claudeService;

import org.example.data.CityData;
import org.example.dtos.City;
import org.example.dtos.ClassificationResult;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.ContentBlock;
import software.amazon.awssdk.services.bedrockruntime.model.ConversationRole;
import software.amazon.awssdk.services.bedrockruntime.model.ConverseResponse;
import software.amazon.awssdk.services.bedrockruntime.model.Message;

import java.util.List;

@Service
public class ClaudeServiceImpl implements IClaudeService {
    private final BedrockRuntimeClient client;

    public ClaudeServiceImpl() {
        this.client = BedrockRuntimeClient.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    @Override
    public ClassificationResult classifyNews(String content) {
        String prompt = String.format(
                """
                Analyze the following news content:
                    %s
                and provide a structured response in the format:
                    Classification: Local or Global
                """, content);

        var message = Message.builder()
                .content(ContentBlock.fromText(prompt))
                .role(ConversationRole.USER)
                .build();

        try {
            ConverseResponse response = client.converse(request -> request
                    .modelId("anthropic.claude-instant-v1")
                    .messages(message)
                    .inferenceConfig(config -> config
                            .maxTokens(512)
                            .temperature(0.5F)
                            .topP(0.9F)));

            var responseText = response.output().message().content().get(0).text();
            System.out.println("Response from model: " + responseText);

            return parseClassificationResult(responseText, content);

        } catch (Exception e) {
            System.err.printf("ERROR: Can't invoke model. Reason: %s\n", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ClassificationResult parseClassificationResult(String responseText, String content) {

        String classification = "unknown";
        if (responseText.toLowerCase().contains("classification:")) {
            String[] parts = responseText.split("\n");
            for (String part : parts) {
                part = part.trim(); // Trim whitespace
                if (part.toLowerCase().startsWith("classification:")) {
                    classification = part.substring("classification:".length()).trim().toLowerCase(); // Extract classification
                }
            }
        } else {
            throw new RuntimeException("Unexpected response format from AI: " + responseText);
        }

        String location = "unknown";
        List<City> knownCities = CityData.getCities();
        for (City city : knownCities) {
            if (content.toLowerCase().contains(city.getName().toLowerCase())) {
                location = city.getName();
                break;
            }
        }

        return new ClassificationResult(classification, location);
    }
}