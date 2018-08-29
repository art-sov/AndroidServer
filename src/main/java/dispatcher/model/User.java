package dispatcher.model;

/**
 * Created by Sovalov.AV on 23.08.2018.
 */
public class User {

    private String authToken;

    public User(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
