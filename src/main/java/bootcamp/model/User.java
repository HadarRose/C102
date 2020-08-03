package bootcamp.model;

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

    @Override
    public String toString(){
        return "name: " + this.name + "\ntwitterHandle: " + this.twitterHandle + "\nprofileImageUrl: " + this.profileImageUrl;
    }

    @Override
    public int hashCode(){
        int result = 17;

        result = 31 * result + name.hashCode();
        result = 31 * result + twitterHandle.hashCode();
        result = 31 * result + profileImageUrl.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof User)){
            return false;
        }

        User user = (User) o;
        if(this.name.equals(user.name) && this.twitterHandle.equals(user.twitterHandle) && this.profileImageUrl.equals(user.profileImageUrl)){
            return true;
        } else {
            return false;
        }
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
