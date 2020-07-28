package configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ConfigurationService;

public class ApplicationConfiguration extends Configuration {
    private static Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @JsonProperty
    private String oauthconsumerkey;
    @JsonProperty
    private String oauthconsumersecret;
    @JsonProperty
    private String oauthaccesstoken;
    @JsonProperty
    private String oauthaccesstokensecret;

    ConfigurationService configurationService;

    /**
     * Constructor, sets path to default value
     */
    public ApplicationConfiguration() {
    }

    /**
     * @return oauthconsumerkey property
     */
    public String getOauthconsumerkey() {
        return oauthconsumerkey;
    }

    /**
     * @return oauthconsumersecret property
     */
    public String getOauthconsumersecret() {
        return oauthconsumersecret;
    }

    /**
     * @return oauthaccesstoken property
     */
    public String getOauthaccesstoken() {
        return oauthaccesstoken;
    }

    /**
     * @return oauthaccesstokensecret property
     */
    public String getOauthaccesstokensecret() {
        return oauthaccesstokensecret;
    }

}