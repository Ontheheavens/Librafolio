package oth.librafolio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ontheheavens
 * @since 05.12.2023
 */
public class Message {

    @JsonProperty("content")
    private String content;

    public Message(String text) {
        this.content = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String text) {
        this.content = text;
    }
}
