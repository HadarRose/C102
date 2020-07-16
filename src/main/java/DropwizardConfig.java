import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;

public class DropwizardConfig extends Configuration{
    @NotEmpty
    private String template; // TODO: figure out what template is for

    @NotEmpty
    private String defaultName = "Stranger"; // TODO: figure out what name is for

    @JsonProperty // TODO: read more about this annotation
    public String getTemplate(){
        return template;
    }

    @JsonProperty
    public void setTemplate(String template){
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName(){
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name){
        this.defaultName = name;
    }
}