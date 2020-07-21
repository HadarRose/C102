package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {
    private int statusCode;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(int code, String message) {
        this.statusCode = code;
        this.message = message;
    }

    @JsonProperty
    public int getStatusCode() {
        return statusCode;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }
}
