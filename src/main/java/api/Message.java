package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message{
    @JsonProperty("message")
    public String message;
}