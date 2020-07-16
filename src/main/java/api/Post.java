package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Post {
    // TODO: change this class to actually match tweets, instead of the hello world program
    private long id;

    private String content;

    public Post(){
        // Jackson deserialization ?????
    }

    public Post(long id, String content){
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId(){
        return id;
    }

    @JsonProperty
    public String getContent(){
        return content;
    }
}
