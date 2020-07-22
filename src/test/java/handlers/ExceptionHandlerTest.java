package handlers;

import org.junit.Before;
import org.junit.Test;
import representations.ErrorMessage;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ExceptionHandlerTest {
    private TwitterException mockedException;

    @Before
    public void initialize() {
        mockedException = mock(TwitterException.class);
    }

    // test generic error
    @Test
    public void generalError() {
        ExceptionHandler eh = spy(new ExceptionHandler());
        Response response = eh.ResponseBuilder(new Exception());
        assertEquals(500, response.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
        assertEquals(eh.GENERAL_ERROR, errorMessage.getMessage());
        verify(eh).GenericException(any(NullPointerException.class));
    }

    // test nullpointerexception
    @Test
    public void nullPointerError() {
        ExceptionHandler eh = new ExceptionHandler();
        Response response = eh.ResponseBuilder(new NullPointerException());
        assertEquals(500, response.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
        assertEquals(eh.BODY_ERROR, errorMessage.getMessage());
    }

    // test general twitterexception
    @Test
    public void generalTwitterError() {
        when(mockedException.getStatusCode()).thenReturn(500);

        ExceptionHandler eh = spy(new ExceptionHandler());
        Response response = eh.ResponseBuilder(mockedException);
        assertEquals(500, response.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
        assertEquals(eh.GENERAL_ERROR, errorMessage.getMessage());
        verify(eh).TwitterException(any(TwitterException.class));

    }

    // test general forbidden twitterexception
    @Test
    public void generalForbiddenTwitterError() {
        when(mockedException.getStatusCode()).thenReturn(403);
        when(mockedException.getErrorCode()).thenReturn(1);
        when(mockedException.getErrorMessage()).thenReturn("error message");

        ExceptionHandler eh = spy(new ExceptionHandler());
        Response response = eh.ResponseBuilder(mockedException);
        assertEquals(403, response.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
        assertEquals("error message", errorMessage.getMessage());
        verify(eh).TwitterException(any(TwitterException.class));
    }

    // test no-content forbidden twitterexception
    @Test
    public void noContentForbiddenTwitterError() {
        when(mockedException.getStatusCode()).thenReturn(403);
        when(mockedException.getErrorCode()).thenReturn(170);
        when(mockedException.getErrorMessage()).thenReturn("error message");

        ExceptionHandler eh = spy(new ExceptionHandler());
        Response response = eh.ResponseBuilder(mockedException);
        assertEquals(403, response.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) response.getEntity();
        assertEquals(eh.NO_CONTENT_ERROR, errorMessage.getMessage());
        verify(eh).TwitterException(any(TwitterException.class));
    }
}