package resources;

import model.Message;
import org.junit.Before;
import org.junit.Test;
import services.twitter4j.TwitterResourceService;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedResourceServices = mock(TwitterResourceService.class);
    }

    // tests that the method calls correct service
    @Test
    public void timelineTest() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
    }

    // tests that the method receives input at postTweet and calls correct service
    @Test
    public void postTest() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        when(mockedResourceServices.postTweet(any(Message.class))).thenAnswer(i -> {
            return Response.ok(i.getArguments()[0]).build();
        });
        Message m = mock(Message.class);
        Response r = twitterRequestResource.postTweet(m);
        Message m_ret = (Message) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        assertEquals(m, m_ret);
    }
}