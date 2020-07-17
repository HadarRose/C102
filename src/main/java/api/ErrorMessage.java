package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import twitter4j.TwitterException;


public class ErrorMessage {
    private int statusCode;
    private String message;

    public ErrorMessage(){}

    public ErrorMessage(TwitterException e){
        this.statusCode = e.getStatusCode();
        this.message = e.getMessage();
    }

    @JsonProperty
    public int getStatusCode(){
        return statusCode;
    }

    @JsonProperty
    public String getMessage(){
        return message;
    }
}
