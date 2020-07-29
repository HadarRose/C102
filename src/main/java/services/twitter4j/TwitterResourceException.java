package services.twitter4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class TwitterResourceException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(TwitterResourceException.class);
    /**
     * the status code associated with a general error
     */
    public static final int GENERAL_ERROR_CODE = 500;
    /**
     * Message for invalid content length
     */
    public static final String CONTENT_LENGTH_ERROR = "The tweet's length is invalid. The content of a tweet must be between 0 and 280 characters long.";
    /**
     * Message for general error
     */
    public static final String GENERAL_ERROR = "Something went wrong.";
    /**
     * Message for errors relating to issues with message bodies
     */
    public static final String BODY_ERROR = "There was an issue reading the body of your request";


    /**
     * The message associated with the error, overriding Exception's message
     */
    private String message;
    /**
     * The status code that a response should have when this exception is thrown
     */
    private int statusCode;

    /**
     * Constructor, casts an Exception to TwitterResourceException.
     *
     * @param exception the exception that this object will expend upon
     */
    public TwitterResourceException(Exception exception) {
        if (exception instanceof TwitterException) {
            TwitterException twitterException = (TwitterException) exception;
            int code = twitterException.getStatusCode();
            this.message = this.GENERAL_ERROR;
            this.statusCode = code;
            if (code == 403) { // forbidden error
                // if this is a empty status problem or a tweet too long error
                if ((twitterException.getErrorCode() == 170) || (twitterException.getErrorCode() == 186)) {
                    this.message = this.CONTENT_LENGTH_ERROR;
                }
            }
        } else {
            if (exception instanceof NullPointerException) {
                this.message = this.BODY_ERROR;
            } else {
                this.message = this.GENERAL_ERROR;
            }
            this.statusCode = GENERAL_ERROR_CODE; // sets status code to default value
        }
        logger.error("TwitterResourceException thrown. It contained the exception: ", exception);
    }

    /**
     * Overrides Exception to return the message from this class and not from the parent, Exception
     *
     * @return String, message property
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * @return int statusCode property
     */
    public int getStatusCode() {
        return this.statusCode;
    }

    /**
     * @param message, String to replace message property
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param statusCode, int to replace statusCode property
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
