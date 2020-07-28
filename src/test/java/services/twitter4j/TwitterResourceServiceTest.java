package services.twitter4j;

import model.Message;
import model.StatusList;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import javax.ws.rs.core.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TwitterResourceServiceTest {
    private Twitter mockedTwitter;
    private TwitterExceptionHandlerService mockedHandlerService;

    @Before
    public void initialize(){
        mockedTwitter = mock(Twitter.class);
        mockedHandlerService = mock(TwitterExceptionHandlerService.class);
    }

    /*TIMELINE TESTS*/
    // tests that if twitter object doesn't throw an error, the response is OK and ResponseBuilder isn't called
    @Test
    public void validTimeline() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedHandlerService);
        ResponseList<Status> mockedList = mock(ResponseList.class);
        when(mockedTwitter.getHomeTimeline()).thenReturn(mockedList);
        Response response = twitterResourceService.getTimeline(mockedTwitter);
        assertEquals(200, response.getStatus());
        verify(mockedHandlerService, never()).ResponseBuilder(any(Exception.class));
        StatusList statusList = (StatusList) response.getEntity();
        assertEquals(mockedList, statusList.getStatusList());
    }

    // tests that if twitter object throws an exception, responsebuilder is called
    @Test
    public void invalidTimeline() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedHandlerService);
        when(mockedTwitter.getHomeTimeline()).thenThrow(Exception.class);
        Response response = twitterResourceService.getTimeline(mockedTwitter);
        verify(mockedHandlerService, times(1)).ResponseBuilder(any(Exception.class));
    }

    /*TWEET TESTS*/
    // tests that if twitter object doesn't throw an error, the response is OK and ResponseBuilder isn't called
    @Test
    public void validPost() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedHandlerService);
        Status mockedStatus = mock(Status.class);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(mockedStatus);
        Response response = twitterResourceService.postTweet(mockedTwitter, new Message("Hello!"));
        assertEquals(200, response.getStatus());
        verify(mockedHandlerService, never()).ResponseBuilder(any(Exception.class));
        Status status = (Status) response.getEntity();
        assertEquals(mockedStatus, status);
    }

    // tests that if twitter object throws an exception, responsebuilder is called
    @Test
    public void invalidPost() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedHandlerService);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenThrow(Exception.class);
        Response response = twitterResourceService.postTweet(mockedTwitter, new Message("Hello!"));
        verify(mockedHandlerService, times(1)).ResponseBuilder(any(Exception.class));
    }

    // tests that if message object throw an exception, response builder is called
    @Test
    public void invalidMessage() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedHandlerService);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenThrow(Exception.class);
        Message m = mock(Message.class);
        when(m.getMessage()).thenThrow(Exception.class);
        Response response = twitterResourceService.postTweet(mockedTwitter, m);
        verify(mockedHandlerService, times(1)).ResponseBuilder(any(Exception.class));
    }
}