package bootcamp.services.twitter4j;

import org.junit.Test;
import twitter4j.TwitterException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TwitterResourceExceptionHandlerTest {

    // tests for the different branches in TwitterResourceException(Exception)
    @Test
    public void generalTwitterException() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(401);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(exception);
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR, twitterResourceExceptionHandler.getMessage());
        assertEquals(401, twitterResourceExceptionHandler.getStatusCode());
    }

    @Test
    public void generalForbidden() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(403);
        when(exception.getErrorCode()).thenReturn(2);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(exception);
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR, twitterResourceExceptionHandler.getMessage());
        assertEquals(403, twitterResourceExceptionHandler.getStatusCode());
    }

    @Test
    public void tweetLengthError() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(403);
        when(exception.getErrorCode()).thenReturn(170);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(exception);
        assertEquals(twitterResourceExceptionHandler.CONTENT_LENGTH_ERROR, twitterResourceExceptionHandler.getMessage());
        assertEquals(403, twitterResourceExceptionHandler.getStatusCode());
    }

    @Test
    public void bodyError() {
        NullPointerException exception = mock(NullPointerException.class);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(exception);
        assertEquals(twitterResourceExceptionHandler.BODY_ERROR, twitterResourceExceptionHandler.getMessage());
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR_CODE, twitterResourceExceptionHandler.getStatusCode());
    }

    @Test
    public void generalError() {
        Exception exception = mock(Exception.class);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(exception);
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR, twitterResourceExceptionHandler.getMessage());
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR_CODE, twitterResourceExceptionHandler.getStatusCode());
    }

    @Test
    public void noSuchElementException() {
        NoSuchElementException elementException = mock(NoSuchElementException.class);

        TwitterResourceExceptionHandler twitterResourceExceptionHandler = new TwitterResourceExceptionHandler(elementException);
        assertEquals(twitterResourceExceptionHandler.MISSING_OPTIONAL, twitterResourceExceptionHandler.getMessage());
        assertEquals(twitterResourceExceptionHandler.GENERAL_ERROR_CODE, twitterResourceExceptionHandler.getStatusCode());
    }
}