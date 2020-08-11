package bootcamp.services.twitter4j;

import bootcamp.model.Message;
import bootcamp.model.Tweet;
import bootcamp.model.User;
import com.google.common.cache.Cache;
import org.junit.Before;
import org.junit.Test;
import twitter4j.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StatusToTweetTest {
    private Twitter mockedTwitter;
    private TwitterResourceService twitterResourceService;
    private Status mockedStatus;
    private twitter4j.User mockUser;
    private User user;
    private Tweet tweet;

    @Before
    public void initialize() {
        // needed variables:
        mockedTwitter = mock(Twitter.class);
        twitterResourceService = new TwitterResourceService(mockedTwitter);
        mockedStatus = mock(Status.class);
        mockUser = mock(twitter4j.User.class);
        user = new User("twitterhandle", "name", "url");
        tweet = new Tweet("message", new Date(), user);
        // when statements:
        when(mockedStatus.getId()).thenReturn(1L);
        when(mockedStatus.getCreatedAt()).thenReturn(tweet.getCreatedAt());
        when(mockedStatus.getUser()).thenReturn(mockUser);
        when(mockedStatus.getText()).thenReturn(tweet.getMessage());
        when(mockUser.getScreenName()).thenReturn(user.getTwitterHandle());
        when(mockUser.getName()).thenReturn(user.getName());
        when(mockUser.getProfileImageURL()).thenReturn(user.getProfileImageUrl());
    }

    // basic test for statusToTweet that makes sure that all of the status variables go into the correct tweet properties
    @Test
    public void testBasicCase() {
        // call statusToTweet
        Tweet resultingTweet = twitterResourceService.statusToTweet(mockedStatus);
        // compare content
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
        // check cache size:
        Cache<String, Tweet> cache = twitterResourceService.getCache();
        assertEquals(1, cache.size());
    }

    // test that if trying to convert tweet that is already in cache, that tweet will be returned from cache
    @Test
    public void testUnchangedWithNew() {
        // call statusToTweet
        Tweet resultingTweet = twitterResourceService.statusToTweet(mockedStatus);
        // compare content
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
        // check cache size:
        Cache<String, Tweet> cache = twitterResourceService.getCache();
        assertEquals(1, cache.size());
        // check that the response is the same:
        Status mockedStatus2 = mock(Status.class);
        when(mockedStatus2.getId()).thenReturn(1L);
        resultingTweet = twitterResourceService.statusToTweet(mockedStatus2);
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
        // check cache size:
        cache = twitterResourceService.getCache();
        assertEquals(1, cache.size());
    }

    // test that if a tweet is added with new ID, it will change the size of the cache
    @Test
    public void testChangedWithNew() {
        // call statusToTweet
        Tweet resultingTweet = twitterResourceService.statusToTweet(mockedStatus);
        // compare content
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
        // check cache size:
        Cache<String, Tweet> cache = twitterResourceService.getCache();
        assertEquals(1, cache.size());
        // change when to return different ID
        when(mockedStatus.getId()).thenReturn(2L);
        // check that the response is the same:
        resultingTweet = twitterResourceService.statusToTweet(mockedStatus);
        assertEquals(tweet.getMessage(), resultingTweet.getMessage());
        assertEquals(tweet.getCreatedAt(), resultingTweet.getCreatedAt());
        assertEquals(tweet.getUser().getName(), resultingTweet.getUser().getName());
        assertEquals(tweet.getUser().getProfileImageUrl(), resultingTweet.getUser().getProfileImageUrl());
        assertEquals(tweet.getUser().getTwitterHandle(), resultingTweet.getUser().getTwitterHandle());
        // check cache size:
        cache = twitterResourceService.getCache();
        assertEquals(2, cache.size());
    }
}
