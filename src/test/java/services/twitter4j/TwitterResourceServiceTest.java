package services.twitter4j;

import model.Message;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;


import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TwitterResourceServiceTest {
    private Twitter mockedTwitter;

    @Before
    public void initialize() {
        mockedTwitter = mock(Twitter.class);
    }

    /*TIMELINE TESTS*/
    // tests that if Twitter returns a status list as expected, the service returns that list
    @Test
    public void validTimeline() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        ResponseList<Status> mockedList = mock(ResponseList.class);
        when(mockedTwitter.getHomeTimeline()).thenReturn(mockedList);
        List<Status> statusList = twitterResourceService.getTimeline();
        assertEquals(mockedList, statusList);
    }

    /*TWEET TESTS*/
    // tests that if Twitter returns a status  as expected, the service returns that status
    @Test
    public void validPost() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        Status mockedStatus = mock(Status.class);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(mockedStatus);
        Status status = twitterResourceService.postTweet(new Message("Hello!"));
        assertEquals(mockedStatus, status);
    }
}