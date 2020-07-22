package representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    @JsonProperty("message")
    private String message;

    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}