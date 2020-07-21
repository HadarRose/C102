package resources;

import api.Message;
import org.junit.Test;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;

import java.sql.Timestamp;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class TwitterRequestResourceTest {
    Twitter mockedTwitter = mock(Twitter.class);


    /*TIMELINE TESTS*/
    // tests call to rootURI/timeline with default constructor
    @Test
    public void validTimeline() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource();
        Response response = twitterRequestResource.getTimeline();
        assertEquals(200, response.getStatus());
    }

    // tests call to rootURI/timeline with bad credentials passed thru constructor
    @Test
    public void invalidTimeline() {
        // create configbuilder, twitterfactory, and set instance as mockedTwitter
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAccessTokenSecret("XXXXXXXXXX");
        TwitterFactory tf = new TwitterFactory(cb.build());
        mockedTwitter = tf.getInstance();
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter);
        Response response = twitterRequestResource.getTimeline();
        assertEquals(401, response.getStatus());
    }

    /*TWEET TESTS*/
    // tests call to rootURI/tweet with valid credentials and message
    @Test
    public void validPost() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // add to post to avoid duplication error
        Message m = new Message("Hello, right now it's: " + timestamp.toString());
        Response response = twitterRequestResource.postTweet(m);
        assertEquals(200, response.getStatus());
    }

    // tests call to rootURI/tweet with invalid credentials
    @Test
    public void invalidCreds() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthAccessTokenSecret("XXXXXXXXXX");
        TwitterFactory tf = new TwitterFactory(cb.build());
        mockedTwitter = tf.getInstance();
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource(mockedTwitter);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // add to post to avoid duplication error
        Message m = new Message("Hello, right now it's: " + timestamp.toString());
        Response response = twitterRequestResource.postTweet(m);
        assertEquals(401, response.getStatus());
    }

    // tests call to rootURI/tweet with valid credentials and null message
    @Test
    public void nullMessage() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource();
        Response response = twitterRequestResource.postTweet(null);
        assertEquals(500, response.getStatus());
    }

    // tests call to rootURI/tweet with valid credentials and empty message
    @Test
    public void emptyMessage() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource();
        Message m = new Message("");
        Response response = twitterRequestResource.postTweet(m);
        assertEquals(403, response.getStatus());
    }

    // tests call to rootURI/tweet with valid credentials and message, but sending the same message twice
    @Test
    public void duplicatePost() {
        TwitterRequestResource twitterRequestResource = new TwitterRequestResource();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis()); // add to post to avoid duplication error
        Message m = new Message("Hello, right now it's: " + timestamp.toString());
        Response response = twitterRequestResource.postTweet(m);
        assertEquals(200, response.getStatus());
        response = twitterRequestResource.postTweet(m); // send the same post again
        assertEquals(403, response.getStatus());
    }

}