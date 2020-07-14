import org.junit.Test;
import twitter4j.TwitterException;

import static org.junit.Assert.*;

public class PostTweetTest {
    // Errors that need handling
    /*
    * 1. no args
    * 2. shouldn't post empty tweet? (throws  a twitter exception, code 170)
    * 3. shouldn't post tweet that is too long (throws a twitter exception, code 186)
    * 4. more than 1 arg (just ignore other args)
    *
    * */

    String[] longTweet = {"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. " +
            "Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec " +
            "quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec."};

    // TEST: exception called for long tweet
//    @Test(expected = IllegalArgumentException.class)
//    public void noArgs() throws TwitterException {
//        String[] noArgs = {};
//        PostTweet.main(noArgs);
//    }
//
//    // TEST: exception called for empty tweet
//    @Test(expected = TwitterException.class)
//    public void tweetEmpty() throws TwitterException {
//        String[] empty = {""};
//        PostTweet.main(empty);
//    }
//
//    // TEST: exception called for long tweet
//    @Test(expected = TwitterException.class)
//    public void tweetTooLong() throws TwitterException {
//        PostTweet.main(longTweet);
//    }

}