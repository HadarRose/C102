package bootcamp.model;

import java.util.Date;

public class Tweet {
    private String message;
    private Date createdAt;
    private User user;

    /**
     * Constructor
     *
     * @param message   (String)
     * @param createdAt (Date)
     * @param user      (User)
     */
    public Tweet(String message, Date createdAt, User user) {
        this.message = message;
        this.createdAt = createdAt;
        this.user = user;
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
}
