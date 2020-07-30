package resources;

import model.ErrorMessage;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import services.twitter4j.TwitterResourceException;
import services.twitter4j.TwitterResourceService;
import twitter4j.Status;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedResourceServices = mock(TwitterResourceService.class);
    }

    // the following tests test that the methods call the correct services and return the correct responses
    @Test
    public void validTimeline() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        List<Status> mockedList = mock(List.class);
        when(mockedResourceServices.getTimeline()).thenReturn(mockedList);

        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        assertEquals(200, r.getStatus());
        List<Status> statusList = (List<Status>) r.getEntity();
        assertEquals(mockedList, statusList);
    }

    @Test
    public void invalidTimeline() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        TwitterResourceException mockedException = mock(TwitterResourceException.class);
        when(mockedResourceServices.getTimeline()).thenThrow(mockedException);
        when(mockedException.getStatusCode()).thenReturn(500);
        when(mockedException.getMessage()).thenReturn("This is an error");

        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        assertEquals(500, r.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        assertEquals("This is an error", errorMessage.getMessage());
    }

    @Test
    public void validPost() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Status mockedStatus = mock(Status.class);
        Message mockedMessage = mock(Message.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenReturn(mockedStatus);

        Response r = twitterRequestResource.postTweet(mockedMessage);
        Status status = (Status) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        assertEquals(200, r.getStatus());
        assertEquals(mockedStatus, status);
    }

    @Test
    public void invalidPost() throws TwitterResourceException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Message mockedMessage = mock(Message.class);
        TwitterResourceException mockedException = mock(TwitterResourceException.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenThrow(mockedException);
        when(mockedException.getStatusCode()).thenReturn(500);
        when(mockedException.getMessage()).thenReturn("This is an error");

        Response r = twitterRequestResource.postTweet(mockedMessage);
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        assertEquals(500, r.getStatus());
        assertEquals("This is an error", errorMessage.getMessage());
    }
}