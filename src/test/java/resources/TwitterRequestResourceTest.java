package resources;

import model.Message;
import org.junit.Before;
import org.junit.Test;
import services.twitter4j.TwitterResourceService;
import twitter4j.*;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private Twitter mockedTwitter;
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedTwitter = mock(Twitter.class);
        mockedResourceServices = mock(TwitterResourceService.class);
    }

    // tests that the method receives input at getTimeline and calls correct service
    @Test
    public void timelineTest() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedResourceServices);
        when(mockedResourceServices.getTimeline(any(Twitter.class))).thenAnswer(i -> {
            return Response.ok(i.getArguments()[0]).build();
        });
        Response r = twitterRequestResource.getTimeline();
        Twitter t = (Twitter) r.getEntity();
        verify(mockedResourceServices, times(1)).getTimeline(any(Twitter.class));
        assertEquals(mockedTwitter, t);
    }

    // tests that the method receives input at postTweet and calls correct service
    @Test
    public void postTest() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedResourceServices);
        when(mockedResourceServices.postTweet(any(Twitter.class), any(Message.class))).thenAnswer(i -> {
            return Response.ok(i.getArguments()[1]).build();
        });
        Message m = mock(Message.class);
        Response r = twitterRequestResource.postTweet(m);
        Message m_ret = (Message) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Twitter.class), any(Message.class));
        assertEquals(m, m_ret);
    }
}