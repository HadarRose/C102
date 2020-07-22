package resources;

import api.ExceptionHandler;
import api.Message;
import org.junit.Test;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import javax.ws.rs.core.Response;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TwitterRequestResourceTest {
    Twitter mockedTwitter = mock(Twitter.class);
    ExceptionHandler mockedHandler = mock(ExceptionHandler.class);


    /*TIMELINE TESTS*/
    // tests that if twitter object doesn't throw an error, the response is OK and ResponseBuilder isn't called
    @Test
    public void validTimeline() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedHandler);
        when(mockedTwitter.getHomeTimeline()).thenReturn(null);
        Response response = twitterRequestResource.getTimeline();
        assertEquals(200, response.getStatus());
        verify(mockedHandler, never()).ResponseBuilder(any(Exception.class));
    }

    // tests that if twitter object throws an exception, responsebuilder is called
    @Test
    public void invalidTimeline() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedHandler);
        when(mockedTwitter.getHomeTimeline()).thenThrow(Exception.class);
        Response response = twitterRequestResource.getTimeline();
        verify(mockedHandler, times(1)).ResponseBuilder(any(Exception.class));
    }

    /*TWEET TESTS*/
    // tests that if twitter object doesn't throw an error, the response is OK and ResponseBuilder isn't called
    @Test
    public void validPost() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedHandler);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(null);
        Response response = twitterRequestResource.postTweet(new Message("Hello!"));
        assertEquals(200, response.getStatus());
        verify(mockedHandler, never()).ResponseBuilder(any(Exception.class));
    }

    // tests that if twitter object throws an exception, responsebuilder is called
    @Test
    public void invalidPost() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedHandler);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenThrow(Exception.class);
        Response response = twitterRequestResource.postTweet(new Message("Hello!"));
        verify(mockedHandler, times(1)).ResponseBuilder(any(Exception.class));
    }
}