package api;

import twitter4j.TwitterException;

import javax.ws.rs.core.Response;

public class ExceptionHandler {
    public static final String GENERAL_ERROR = "Something went wrong.";

    /** Handles all exceptions by finding their type and handling them accodingly.
     * @return Response, response generated according to error
     * */
    public static Response ExceptionHandler(Exception e){
        ErrorMessage errorMessage;
        if(e instanceof TwitterException){
            errorMessage = TwitterException((TwitterException) e);
        } else {
            errorMessage = GenericException(e);
        }
        return Response.status(errorMessage.getStatusCode()).entity(errorMessage).build();
    }

    /** Handles TwitterExceptions
     * @return ErrorMessage, if status 403 the message will be specified by the twitter4j library.
     *          Else, it will be the general error message
     * */
    public static ErrorMessage TwitterException(TwitterException e){
        int code = e.getStatusCode();
        if(code == 403){
            return new ErrorMessage(code, e.getErrorMessage());
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
