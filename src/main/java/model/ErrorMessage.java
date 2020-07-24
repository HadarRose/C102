package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorMessage {
    private static Logger logger = LoggerFactory.getLogger(ErrorMessage.class);

    private int statusCode;
    private String message;

    /**
     * Constructor. Doesn't set up properties to anything.
     */
    public ErrorMessage() {
    }

    /**
     * Constructor.
     *
     * @param code    value for statusCode property
     * @param message value for message property
     */
    public ErrorMessage(int code, String message) {
        this.statusCode = code;
        this.message = message;
        logger.debug("Created ErrorMessage with code={} and message={}", code, message);
    }

    /**
     * @return int. the object's statusCode property
     */
    @JsonProperty
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return String. the object's message property.
     */
    @JsonProperty
    public String getMessage() {
        return message;
    }
}
