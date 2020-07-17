    // TODO: make sure you got rid of this

package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

    public class Post {
    /*@NotEmpty
    @Length(max = 280, min=1)*/
    private String message;

    public Post(){
    }

    public Post(String message){
        this.message = message;
    }


    @JsonProperty
    public String getMessage(){
        return message;
    }
}
