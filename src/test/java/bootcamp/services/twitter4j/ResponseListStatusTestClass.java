package bootcamp.services.twitter4j;

import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;

import java.util.ArrayList;

/* note to self about how I did this: extended a class that already implements most of the methods that are needed
 * for ResponseList<T>*/
public class ResponseListStatusTestClass<T> extends ArrayList<T> implements ResponseList<T> {

    @Override
    public RateLimitStatus getRateLimitStatus() {
        return null;
    }

    @Override
    public int getAccessLevel() {
        return 0;
    }
}