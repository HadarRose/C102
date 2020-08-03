package bootcamp.services.twitter4j;

import org.junit.Test;
import twitter4j.TwitterException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TwitterResourceExceptionTest {

    // tests for the different branches in TwitterResourceException(Exception)
    @Test
    public void generalTwitterException() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(401);

        TwitterResourceException twitterResourceException = new TwitterResourceException(exception);
        assertEquals(twitterResourceException.GENERAL_ERROR, twitterResourceException.getMessage());
        assertEquals(401, twitterResourceException.getStatusCode());
    }

    @Test
    public void generalForbidden() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(403);
        when(exception.getErrorCode()).thenReturn(2);

        TwitterResourceException twitterResourceException = new TwitterResourceException(exception);
        assertEquals(twitterResourceException.GENERAL_ERROR, twitterResourceException.getMessage());
        assertEquals(403, twitterResourceException.getStatusCode());
    }

    @Test
    public void tweetLengthError() {
        TwitterException exception = mock(TwitterException.class);
        when(exception.getStatusCode()).thenReturn(403);
        when(exception.getErrorCode()).thenReturn(170);

        TwitterResourceException twitterResourceException = new TwitterResourceException(exception);
        assertEquals(twitterResourceException.CONTENT_LENGTH_ERROR, twitterResourceException.getMessage());
        assertEquals(403, twitterResourceException.getStatusCode());
    }

    @Test
    public void bodyError() {
        NullPointerException exception = mock(NullPointerException.class);

        TwitterResourceException twitterResourceException = new TwitterResourceException(exception);
        assertEquals(twitterResourceException.BODY_ERROR, twitterResourceException.getMessage());
        assertEquals(twitterResourceException.GENERAL_ERROR_CODE, twitterResourceException.getStatusCode());
    }

    @Test
    public void generalError() {
        Exception exception = mock(Exception.class);

        TwitterResourceException twitterResourceException = new TwitterResourceException(exception);
        assertEquals(twitterResourceException.GENERAL_ERROR, twitterResourceException.getMessage());
        assertEquals(twitterResourceException.GENERAL_ERROR_CODE, twitterResourceException.getStatusCode());
    }

    @Test
    public void noSuchElementException() {
        NoSuchElementException elementException = mock(NoSuchElementException.class);

        TwitterResourceException twitterResourceException = new TwitterResourceException(elementException);
        assertEquals(twitterResourceException.MISSING_OPTIONAL, twitterResourceException.getMessage());
        assertEquals(twitterResourceException.GENERAL_ERROR_CODE, twitterResourceException.getStatusCode());
    }
}