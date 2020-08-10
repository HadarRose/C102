package bootcamp.resources;

import bootcamp.model.ErrorMessage;
import bootcamp.model.Message;
import bootcamp.model.Tweet;
import com.google.googlejavaformat.Op;
import org.junit.Before;
import org.junit.Test;
import bootcamp.services.twitter4j.TwitterResourceExceptionHandler;
import bootcamp.services.twitter4j.TwitterResourceService;

import javax.swing.text.html.Option;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TwitterRequestResourceTest {
    private TwitterResourceService mockedResourceServices;

    @Before
    public void initialize() {
        mockedResourceServices = mock(TwitterResourceService.class);
    }

    // the following tests test that the methods call the correct bootcamp.services and return the correct responses
    @Test
    public void validTimeline() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        List<Tweet> mockedList = mock(List.class);
        when(mockedResourceServices.getTimeline()).thenReturn(Optional.ofNullable(mockedList));

        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        assertEquals(200, r.getStatus());
        List<Tweet> statusList = (List<Tweet>) r.getEntity();
        assertEquals(mockedList, statusList);
    }

    @Test
    public void invalidTimeline() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        when(mockedResourceServices.getTimeline()).thenReturn(Optional.empty());

        Response r = twitterRequestResource.getTimeline();
        verify(mockedResourceServices, times(1)).getTimeline();
        assertEquals(500, r.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        assertEquals(TwitterRequestResource.SERVICE_ERROR, errorMessage.getMessage());
    }

    @Test
    public void validPost() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Tweet mockedTweet = mock(Tweet.class);
        Message mockedMessage = mock(Message.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenReturn(Optional.ofNullable(mockedTweet));

        Response r = twitterRequestResource.postTweet(mockedMessage);
        Tweet tweet = (Tweet) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        assertEquals(200, r.getStatus());
        assertEquals(mockedTweet, tweet);
    }

    @Test
    public void invalidPost() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        Message mockedMessage = mock(Message.class);
        when(mockedResourceServices.postTweet(any(Message.class))).thenReturn(Optional.empty());

        Response r = twitterRequestResource.postTweet(mockedMessage);
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        verify(mockedResourceServices, times(1)).postTweet(any(Message.class));
        assertEquals(500, r.getStatus());
        assertEquals(TwitterRequestResource.SERVICE_ERROR, errorMessage.getMessage());
    }

    @Test
    public void validTimelineFilter() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        List<Tweet> mockedList = mock(List.class);
        when(mockedResourceServices.getTimelineFiltered(any(Optional.class))).thenReturn(Optional.ofNullable(mockedList));

        Response r = twitterRequestResource.getTimelineFiltered(null);
        verify(mockedResourceServices, times(1)).getTimelineFiltered(any(Optional.class));
        assertEquals(200, r.getStatus());
        List<Tweet> statusList = (List<Tweet>) r.getEntity();
        assertEquals(mockedList, statusList);
    }

    @Test
    public void invalidTimelineFilter() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedResourceServices);
        when(mockedResourceServices.getTimelineFiltered(any(Optional.class))).thenReturn(Optional.empty());

        Response r = twitterRequestResource.getTimelineFiltered(null);
        verify(mockedResourceServices, times(1)).getTimelineFiltered(any(Optional.class));
        assertEquals(500, r.getStatus());
        ErrorMessage errorMessage = (ErrorMessage) r.getEntity();
        assertEquals(TwitterRequestResource.SERVICE_ERROR, errorMessage.getMessage());
    }
}