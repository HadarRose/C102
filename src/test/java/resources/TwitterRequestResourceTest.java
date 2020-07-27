package resources;

import model.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import services.twitter4j.TwitterResourceService;
import twitter4j.*;

import javax.ws.rs.core.Response;

import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.junit.Assert.*;


// TODO: clean up, JavaDoc
// TODO: pretty much redo all tests
// idea for how to do this: when to return response with mocked twitter as entry
public class TwitterRequestResourceTest {
    private Twitter mockedTwitter;
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedTwitter = mock(Twitter.class);
        mockedResourceServices = mock(TwitterResourceService.class);
    }

    @Test
    public void timelineTest(){
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedResourceServices);
        when(mockedResourceServices.getTimeline(any(Twitter.class))).thenAnswer(i ->{
            return Response.ok(i.getArguments()[0]).build();
        });
        Response r = twitterRequestResource.getTimeline();
        Twitter t = (Twitter) r.getEntity();
        assertEquals(mockedTwitter, t);
    }

    @Test
    public void postTest(){
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter, mockedResourceServices);
        when(mockedResourceServices.postTweet(any(Twitter.class), any(Message.class))).thenAnswer(i ->{
            return Response.ok(i.getArguments()[1]).build();
        });
        Message m = mock(Message.class);
        Response r = twitterRequestResource.postTweet(m);
        Message m_ret = (Message) r.getEntity();
        assertEquals(m, m_ret);
    }
}