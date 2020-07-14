import twitter4j.TwitterException;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.List;


public class C102_Runner {
    public static void main(String[] args) throws TwitterException {
        if(args.length == 0) { // check for error: no argument
            throw new IllegalArgumentException("This program must have an argument");
        }
        if(args[0].equals("post")){
            try {
                PostTweet.postTweet(args[1]);
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("You must specify post content.");
            }
        } else if (args[0].equals("timeline")){
            GetTimeline.getTimeline();
        } else {
            throw new IllegalArgumentException("Argument must be either 'post' or 'timeline'.");
        }

    }

}
