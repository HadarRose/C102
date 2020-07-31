package model;

public class User {
    private String twitterHandle;
    private String name;
    private String profileImageUrl;

    /**
     * Constructor.
     *
     * @param twitterHandle   (String)
     * @param name            (String)
     * @param profileImageUrl (String)
     */
    public User(String twitterHandle, String name, String profileImageUrl) {
        this.name = name;
        this.twitterHandle = twitterHandle;
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * @return String twitterHandle
     */
    public String getTwitterHandle() {
        return twitterHandle;
    }

    /**
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * @return String profileImageUrk
     */
    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    /**
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param profileImageUrl String
     */
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    /**
     * @param twitterHandle String
     */
    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }
}
