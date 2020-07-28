package resources;

import model.ErrorMessage;
import model.Message;
import org.junit.Before;
import org.junit.Test;
import services.twitter4j.TwitterExceptionHandlerService;
import services.twitter4j.TwitterResourceService;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.ws.rs.core.Response;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private TwitterResourceService mockedResourceServices;
    private TwitterExceptionHandlerService mockedHandler;

    @Before
    public void initialize() {
        mockedResourceServices = mock(TwitterResourceService.class);
        mockedHandler = mock(TwitterExceptionHandlerService.class);
    }

    // the following tests test that the methods call the correct services and return the correct responses
    @Test
    public void validTimeline() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices, mockedHandler);
        List<Status> mockedList = mock(List.class);
        when(mockedResourceServices.getTimeline()).thenReturn(mockedList);
        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        verify(mockedHandler, never()).ErrorMessageBuilder(any(Exception.class));
        assertEquals(200, r.getStatus());
        List<Status> statusList = (List<Status>) r.getEntity();
        assertEquals(mockedList, statusList);
    }

    @Test
    public void invalidTimeline() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices, mockedHandler);
        when(mockedResourceServices.getTimeline()).thenThrow(Exception.class);
        when(mockedHandler.ErrorMessageBuilder(any(Exception.class))).thenReturn(new ErrorMessage(500, "This is an error"));

        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        verify(mockedHandler, times(1)).ErrorMessageBuilder(any(Exception.class));
        assertEquals(500, r.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        assertEquals("This is an error", errorMessage.getMessage());
    }

    @Test
    public void validPost() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices, mockedHandler);
        Status mockedStatus = mock(Status.class);
        Message mockedMessage = mock(Message.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenReturn(mockedStatus);

        Response r = twitterRequestResource.postTweet(mockedMessage);
        Status status = (Status) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        verify(mockedHandler, never()).ErrorMessageBuilder(any(Exception.class));
        assertEquals(200, r.getStatus());
        assertEquals(mockedStatus, status);
    }

    @Test
    public void invalidPost() throws TwitterException {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices, mockedHandler);
        Message mockedMessage = mock(Message.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenThrow(Exception.class);
        when(mockedHandler.ErrorMessageBuilder(any(Exception.class))).thenReturn(new ErrorMessage(500, "This is an error"));

        Response r = twitterRequestResource.postTweet(mockedMessage);
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        verify(mockedHandler, times(1)).ErrorMessageBuilder(any(Exception.class));
        assertEquals(500, r.getStatus());
        assertEquals("This is an error", errorMessage.getMessage());
    }
}