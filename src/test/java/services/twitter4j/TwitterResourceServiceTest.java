package services.twitter4j;

import model.Message;
import model.Tweet;
import model.User;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class TwitterResourceServiceTest {
    private Twitter mockedTwitter;

    /* note to self about how I did this: extended a class that already implements most of the methods that are needed
     * for ResponseList<T>*/
    public class ResponseListStatus<T> extends ArrayList<T> implements ResponseList<T> {

        @Override
        public RateLimitStatus getRateLimitStatus() {
            return null;
        }

        @Override
        public int getAccessLevel() {
            return 0;
        }
    }

    @Before
    public void initialize() {
        mockedTwitter = mock(Twitter.class);
    }

    /*TIMELINE TESTS*/
    /*tests that when TwitterResourceService.getTimeline() is called, it will produce a status and send that
     * status to its statusToTweet method. The validity of statusToTweet is tested elsewhere.*/
    @Test
    public void validTimeline() throws TwitterException, TwitterResourceException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Tweet mockedTweet = mock(Tweet.class);
        Status mockedStatus = mock(Status.class);
        ResponseListStatus<Status> listStatus = new ResponseListStatus<>();
        listStatus.add(mockedStatus);
        listStatus.add(mockedStatus);
        when(mockedTwitter.getHomeTimeline()).thenReturn(listStatus);
        doReturn(mockedTweet).when(twitterResourceService).statusToTweet(mockedStatus);
        List<Tweet> tweets = twitterResourceService.getTimeline();
        verify(twitterResourceService, times(2)).statusToTweet(mockedStatus);
    }

    // tests that if the internal Twitter object throws an error, the method throws a TwitterResourceException
    @Test(expected = TwitterResourceException.class)
    public void invalidTimeline() throws TwitterException, TwitterResourceException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        when(mockedTwitter.getHomeTimeline()).thenThrow(TwitterException.class);
        List<Tweet> tweetList = twitterResourceService.getTimeline();
    }

    /*TWEET TESTS*/

    /*tests that when TwitterResourceService.postTweet() is called, it will produce a status and send that
     * status to its statusToTweet method. The validity of statusToTweet is tested elsewhere.*/
    @Test
    public void validPost() throws TwitterException, TwitterResourceException {
        TwitterResourceService twitterResourceService = spy(new TwitterResourceService(mockedTwitter));
        Status mockedStatus = mock(Status.class);
        Tweet mockedTweet = mock(Tweet.class);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenReturn(mockedStatus);
        doReturn(mockedTweet).when(twitterResourceService).statusToTweet(mockedStatus);
        Tweet tweet = twitterResourceService.postTweet(new Message("Hello!"));
        verify(twitterResourceService, times(1)).statusToTweet(mockedStatus);
    }

    // tests that if the internal Twitter object throws an error, the method throws a TwitterResourceException
    @Test(expected = TwitterResourceException.class)
    public void invalidPost() throws TwitterException, TwitterResourceException {
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        when(mockedTwitter.updateStatus(any(StatusUpdate.class))).thenThrow(TwitterException.class);
        Tweet tweet = twitterResourceService.postTweet(new Message("Hello!"));
    }

    /*OTHER TESTS*/
    // test for statusToTweet that makes sure that all of the status variables go into the correct tweet properties
    @Test
    public void testStatusToTweet() {
        // needed variables:
        TwitterResourceService twitterResourceService = new TwitterResourceService(mockedTwitter);
        Status mockedStatus = mock(Status.class);
        twitter4j.User mockUser = mock(twitter4j.User.class);
        User user = new User("twitterhandle", "name", "url");
        Tweet tweet = new Tweet("message", new Date(), user);
        // when statements
        when(mockedStatus.getCreatedAt()).thenReturn(tweet.getCreatedAt());
        when(mockedStatus.getUser()).thenReturn(mockUser);
        when(mockedStatus.getText()).thenReturn(tweet.getMessage());
        when(mockUser.getScreenName()).thenReturn(user.getTwitterHandle());
        when(mockUser.getName()).thenReturn(user.getName());
        when(mockUser.getProfileImageURL()).thenReturn(user.getProfileImageUrl());
        // call statusToTweet
        Tweet resultingTweet = twitterResourceService.statusToTweet(mockedStatus);
        // compare content
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
    }
}