package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

public class ErrorMessage {
    private int statusCode;
    private String message;

    public ErrorMessage(){
        // TODO: Jackson deserialization ?????
    }

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
