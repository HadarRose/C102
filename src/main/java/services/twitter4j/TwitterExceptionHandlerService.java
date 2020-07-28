package services.twitter4j;

import model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.TwitterException;

public class TwitterExceptionHandlerService {
    public static final String GENERAL_ERROR = "Something went wrong.";
    public static final String BODY_ERROR = "There was an issue reading the body of your request";
    public static final String CONTENT_LENGTH_ERROR = "The tweet's length is invalid. The content of a tweet must be between 0 and 280 characters long.";

    private static Logger logger = LoggerFactory.getLogger(TwitterExceptionHandlerService.class);

    /**
     * Handles all exceptions by finding their type and handling them accordingly.
     *
     * @return ErrorMessage containing appropriate message and error code
     */
    public ErrorMessage ErrorMessageBuilder(Exception e) {
        ErrorMessage errorMessage;
        if (e instanceof TwitterException) {
            logger.debug("Error recognized as TwitterException");
            errorMessage = TwitterException((TwitterException) e);
        } else if (e instanceof NullPointerException) {
            logger.debug("Error recognized as NullPointerException");
            errorMessage = new ErrorMessage(500, this.BODY_ERROR);
        } else {
            logger.debug("Error is neither a TwitterException nor a NullPointerException");
            errorMessage = GenericException(e);
        }
        return errorMessage;
    }

    /**
     * Handles TwitterExceptions
     *
     * @return ErrorMessage, if status 403 the message will be specified by the twitter4j library.
     * Else, it will be the general error message
     */
    public ErrorMessage TwitterException(TwitterException e) {
        int code = e.getStatusCode();
        if (code == 403) { // forbidden error
            logger.debug("Error recognized as Forbidden (Status 403)");
            String message = e.getErrorMessage();
            // if this is a empty status problem or a tweet too long error
            if ((e.getErrorCode() == 170) || (e.getErrorCode() == 186)) {
                message = this.CONTENT_LENGTH_ERROR;
            }
            return new ErrorMessage(code, message);
        } else {
            logger.debug("Error recognized as non-forbidden TwitterException");
            return new ErrorMessage(code, TwitterExceptionHandlerService.GENERAL_ERROR);
        }
    }

    /**
     * Handles errors that don't have a specific case
     *
     * @return ErrorMessage, status 500 and message is the general error message
     */
    public ErrorMessage GenericException(Exception e) {
        return new ErrorMessage(500, TwitterExceptionHandlerService.GENERAL_ERROR);
    }
}
