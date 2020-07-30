package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitterKeys {
    private String oauthconsumerkey;
    private String oauthconsumersecret;
    private String oauthaccesstoken;
    private String oauthaccesstokensecret;

    /**
     * @return oauthconsumerkey property
     */
    @JsonProperty
    public String getOauthconsumerkey() {
        return oauthconsumerkey;
    }

    /**
     * @return oauthconsumersecret property
     */
    @JsonProperty
    public String getOauthconsumersecret() {
        return oauthconsumersecret;
    }

    /**
     * @return oauthaccesstoken property
     */
    @JsonProperty
    public String getOauthaccesstoken() {
        return oauthaccesstoken;
    }

    /**
     * @return oauthaccesstokensecret property
     */
    @JsonProperty
    public String getOauthaccesstokensecret() {
        return oauthaccesstokensecret;
    }

    /**
     * @param oauthconsumerkey String to replace oauthconsumer property
     */
    @JsonProperty
    public void setOauthconsumerkey(String oauthconsumerkey) {
        this.oauthconsumerkey = oauthconsumerkey;
    }

    /**
     * @param oauthconsumersecret String to replace oauthconsumersecret property
     */
    @JsonProperty
    public void setOauthconsumersecret(String oauthconsumersecret) {
        this.oauthconsumersecret = oauthconsumersecret;
    }

    /**
     * @param oauthaccesstoken String to replace oauthaccesstoken property
     */
    @JsonProperty
    public void setOauthaccesstoken(String oauthaccesstoken) {
        this.oauthaccesstoken = oauthaccesstoken;
    }

    /**
     * @param oauthaccesstokensecret String to replace oauthaccesstokensecret property
     */
    @JsonProperty
    public void setOauthaccesstokensecret(String oauthaccesstokensecret) {
        this.oauthaccesstokensecret = oauthaccesstokensecret;
    }
}
