package resources;

import api.ExceptionHandler;
import api.Message;
import api.StatusList;
import org.junit.Test;
import twitter4j.*;

import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TwitterRequestResourceTest {
    Twitter mockedTwitter = mock(Twitter.class);
    ExceptionHandler mockedHandler = mock(ExceptionHandler.class);
// TODO: test responses fully

    /*TIMELINE TESTS*/
    // tests that if twitter object doesn't throw an error, the response is OK and ResponseBuilder isn't called
    @Test
    public void validTimeline() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedHandler);
        ResponseList<Status> mockedList = mock(ResponseList.class);
        when(mockedTwitter.getHomeTimeline()).thenReturn(mockedList);
        Response response = twitterRequestResource.getTimeline();
        assertEquals(200, response.getStatus());
        verify(mockedHandler, never()).ResponseBuilder(any(Exception.class));
        StatusList statusList = (StatusList) response.getEntity();
        assertEquals(mockedList, statusList.getStatusList());

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
        Status mockedStatus = mock(Status.class);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(mockedStatus);
        Response response = twitterRequestResource.postTweet(new Message("Hello!"));
        assertEquals(200, response.getStatus());
        verify(mockedHandler, never()).ResponseBuilder(any(Exception.class));
        Status status = (Status) response.getEntity();
        assertEquals(mockedStatus, status);
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