package api;

import twitter4j.TwitterException;

import javax.ws.rs.core.Response;
import java.util.Arrays;

public class ExceptionHandler {
    public static final String GENERAL_ERROR = "Something went wrong.";
    public static final int[] ACCEPTABLE_TWITTER_CODES = {403, 401};


    /** Handles all exceptions by finding their type and handling them accordingly.
     * @return Response, response generated according to error
     * */
    public static Response ExceptionHandler(Exception e){
        ErrorMessage errorMessage;
        if(e instanceof TwitterException){
            errorMessage = TwitterException((TwitterException) e);
        } else if(e instanceof NullPointerException){
            errorMessage = new ErrorMessage(500, "There was an issue reading the body of your request");
        } else {
            errorMessage = GenericException(e);
        }
        return Response.status(errorMessage.getStatusCode()).entity(errorMessage).build();
    }

    /** Handles TwitterExceptions
     * @return ErrorMessage, if status 403 the message will be specified by the twitter4j library.
     *          Else, it will be the general error message
     * */
    public static ErrorMessage TwitterException(TwitterException e){ // TODO: have better messages than the twitter4j ones
        int code = e.getStatusCode();
        if(Arrays.stream(ExceptionHandler.ACCEPTABLE_TWITTER_CODES).anyMatch(i -> i == code)){ // check if code is an acceptable one
            String message = e.getErrorMessage();
            if(e.getErrorCode() == 170){ // if this is a empty status problem
                message = "No tweet content specified.";
            }

            return new ErrorMessage(code, message);
        } else {
            return new ErrorMessage(code, ExceptionHandler.GENERAL_ERROR);
        }
    }


    /** Handles errors that don't have a specific case
     * @return ErrorMessage, status 500 and message is the general error message
     * */
    public static ErrorMessage GenericException(Exception e){
        return new ErrorMessage(500, ExceptionHandler.GENERAL_ERROR);
    }
}
