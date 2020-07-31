package bootcamp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Message {
    private static Logger logger = LoggerFactory.getLogger(Message.class);

    private String message;

    /**
     * Constructor. Doesn't set up properties to anything.
     */
    public Message() {
    }

    /**
     * Constructor.
     *
     * @param message value for message property
     */
    public Message(String message) {
        logger.debug("Message created with message = {}", message);
        this.message = message;
    }

    /**
     * @return String. This object's message property.
     */
    @JsonProperty
    public String getMessage() {
        return message;
    }
}