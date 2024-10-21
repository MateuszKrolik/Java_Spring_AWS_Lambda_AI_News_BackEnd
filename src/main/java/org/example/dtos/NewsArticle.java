package org.example.dtos;

public class NewsArticle {
    private String title;
    private String content;
    private String location;

    public NewsArticle(String title, String content, String location) {
        this.title = title;
        this.content = content;
        this.location = location;
    }

    public NewsArticle() {
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}