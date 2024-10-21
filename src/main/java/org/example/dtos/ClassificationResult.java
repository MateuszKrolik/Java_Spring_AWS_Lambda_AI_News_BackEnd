package org.example.dtos;

public class ClassificationResult {
    private String classification;
    private String location;

    public ClassificationResult(String classification, String location) {
        this.classification = classification;
        this.location = location;
    }

    public ClassificationResult() {
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
