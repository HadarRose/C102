package bootcamp.model;

import java.util.Date;
public class Tweet {
    private String message;
    private Date createdAt;
    private String id;
    private User user;

    /**
     * Constructor
     *
     * @param message   (String)
     * @param createdAt (Date)
     * @param user      (User)
     */
    public Tweet(String message, Date createdAt, String id, User user) {
        this.message = message;
        this.createdAt = createdAt;
        this.id = id;
        this.user = user;
    }

    @Override
    public String toString(){
        return "message: " + message + "\ncreatedAt" + createdAt.toString()
                + "\nsource" + id + "\nuser: " + user.toString();
    }

    @Override
    public int hashCode(){
        int result = 17;

        result = 31 * result + message.hashCode();
        result = 31 * result + createdAt.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + user.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Tweet)){
            return false;
        }

        Tweet tweet = (Tweet) o;
        if(this.user.equals(tweet.user) && this.createdAt.equals(tweet.createdAt)
                && this.message.equals(tweet.message) && this.id.equals(tweet.id)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return Date createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return String source
     * */
    public String getId() {
        return id;
    }

    /**
     * @return User user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @param id
     * */
    public void setId(String id) {
        this.id = id;
    }
}
