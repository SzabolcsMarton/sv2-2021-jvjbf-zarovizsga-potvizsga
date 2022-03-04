package message;

public class User {

    private long id;
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

    public User(long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return userName;
    }
}
