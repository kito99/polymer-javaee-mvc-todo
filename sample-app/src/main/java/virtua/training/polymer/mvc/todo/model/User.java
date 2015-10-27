package virtua.training.polymer.mvc.todo.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Represents a user.
 *
 * Created: 22 Oct 2015
 *
 * @author Kito D. Mann
 */
public class User implements Serializable {

    @NotNull
    @Pattern(regexp = "^([1-zA-Z0-1@.\\s]{2,10})$",
            message = "{InvalidUserId}")
    private String userId;

    @NotNull
    @Pattern(regexp = "^(?=[^_].*?\\d)\\w(\\w|[!@#$%]){4,20}",
            message = "{InvalidPassword}")
    private String password;

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
