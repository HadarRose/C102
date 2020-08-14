package bootcamp.services.twitter4j;

import bootcamp.model.Message;
import bootcamp.model.Tweet;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.List;
import java.util.Optional;

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
    /*tests that when TwitterResourceService.getTimeline() is called, it will produce a status and send that
     * status to its statusToTweet method. The validity of statusToTweet is tested elsewhere.*/
    @Test
    public void validTimeline() throws TwitterException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Tweet mockedTweet = mock(Tweet.class);
        Status mockedStatus = mock(Status.class);
        ResponseListStatusTestClass<Status> listStatus = new ResponseListStatusTestClass<>();
        listStatus.add(mockedStatus);
        listStatus.add(mockedStatus);
        when(mockedTwitter.getHomeTimeline()).thenReturn(listStatus);
        doReturn(mockedTweet).when(twitterResourceService).statusToTweet(mockedStatus);
        Optional<List<Tweet>> tweets = twitterResourceService.getTimeline();
        verify(twitterResourceService, times(2)).statusToTweet(mockedStatus);
        tweets = twitterResourceService.getTimeline(); // call it again to test caching
        verify(mockedTwitter, times(1)).getHomeTimeline(); // verify that the cache loaded only once
    }

    // tests that if the internal Twitter object throws an error, the method returns an empty optional
    @Test()
    public void invalidTimeline() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        when(mockedTwitter.getHomeTimeline()).thenThrow(TwitterException.class);
        Optional<List<Tweet>> tweetList = twitterResourceService.getTimeline();
        assertFalse(tweetList.isPresent());
    }

    // tests that getTimelineFiltered correctly returns only filtered Statuses (which are converted to Tweets)
    @Test
    public void filteredTimelineTest() throws TwitterException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Optional<String> keyword = Optional.of("hello");
        Status mockedStatus1 = mock(Status.class);
        Status mockedStatus2 = mock(Status.class);
        Tweet mockedTweet = mock(Tweet.class);
        ResponseListStatusTestClass<Status> listStatus = new ResponseListStatusTestClass<>();
        listStatus.add(mockedStatus1);
        listStatus.add(mockedStatus2);

        when(mockedStatus1.getText()).thenReturn("Hello there");
        when(mockedStatus2.getText()).thenReturn("goodbye");
        when(mockedTwitter.getHomeTimeline()).thenReturn(listStatus);
        doReturn(mockedTweet).when(twitterResourceService).statusToTweet(mockedStatus1);

        Optional<List<Tweet>> tweets = twitterResourceService.getTimelineFiltered(keyword);

        // assert that statusToTweet was called for the first mockedStatus but not the other
        verify(twitterResourceService, times(1)).statusToTweet(mockedStatus1);
        verify(twitterResourceService, never()).statusToTweet(mockedStatus2);

        // assert that the list only has one element, which is the mockedtweet
        assertEquals(1, tweets.get().size());
        assertTrue(tweets.get().contains(mockedTweet));

        // call again and test number of twitter calls
        tweets = twitterResourceService.getTimelineFiltered(keyword);
        verify(mockedTwitter, times(1)).getHomeTimeline();

    }

    // tests that having a null optional returns an empty optional
    @Test()
    public void filteredTimelineOptionalException() throws TwitterException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Status mockedStatus = mock(Status.class);
        ResponseListStatusTestClass<Status> listStatus = new ResponseListStatusTestClass<>();
        listStatus.add(mockedStatus);

        when(mockedStatus.getText()).thenReturn("hello there");
        when(mockedTwitter.getHomeTimeline()).thenReturn(listStatus);

        Optional<List<Tweet>> tweets = twitterResourceService.getTimelineFiltered(null);
        assertFalse(tweets.isPresent());
    }

    /*TWEET TESTS*/

    /*tests that when TwitterResourceService.postTweet() is called, it will produce a status and send that
     * status to its statusToTweet method. The validity of statusToTweet is tested elsewhere.*/
    @Test
    public void validPost() throws TwitterException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Status mockedStatus = mock(Status.class);
        Tweet mockedTweet = mock(Tweet.class);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(mockedStatus);
        doReturn(mockedTweet).when(twitterResourceService).statusToTweet(mockedStatus);
        Optional<Tweet> tweet = twitterResourceService.postTweet(new Message("Hello!"));
        verify(twitterResourceService, times(1)).statusToTweet(mockedStatus);
    }

    // tests that if the internal Twitter object throws an error, the method returns an empty optional
    @Test
    public void invalidPost() throws TwitterException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenThrow(TwitterException.class);
        Optional<Tweet> tweet = twitterResourceService.postTweet(new Message("Hello!"));
        assertFalse(tweet.isPresent());
    }
}