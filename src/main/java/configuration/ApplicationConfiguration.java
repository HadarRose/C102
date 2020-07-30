package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {
    private TwitterKeys twitterKeys;

    /**
     * @return TwitterKeys, twitterKeys property
     */
    @JsonProperty
    public TwitterKeys getTwitterKeys() {
        return twitterKeys;
    }

    /**
     * @param twitterKeys TwitterKeys to replace twitterKeys value
     */
    @JsonProperty
    public void setTwitterKeys(TwitterKeys twitterKeys) {
        this.twitterKeys = twitterKeys;
    }
}