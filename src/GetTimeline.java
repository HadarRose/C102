import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;

public class GetTimeline {
    public static void main(String[] args) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statusList = twitter.getHomeTimeline();
        System.out.println("Timeline retrieved");
        for(Status status: statusList){
            System.out.println(status.getUser().getName()+":"+status.getText());
        }
    }
}
