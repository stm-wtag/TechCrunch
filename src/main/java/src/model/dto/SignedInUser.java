package src.model.dto;

public class SignedInUser {

    private final int id;
    private final String userName;
    private final String token;

    public SignedInUser(int id, String userName, String token) {
        this.id = id;
        this.userName = userName;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }

}
