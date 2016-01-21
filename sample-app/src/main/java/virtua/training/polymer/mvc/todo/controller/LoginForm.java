package virtua.training.polymer.mvc.todo.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

/**
 * Collects login parameters.
 *
 * @author Kito D. Mann
 */
public class LoginForm {

    @NotNull
    @Pattern(regexp = "^([1-zA-Z0-1@.\\s]{2,10})$",
            message = "{InvalidUserId}")
    @FormParam("userId")
    private String userId;

    @NotNull
    @Pattern(regexp = "^(?=[^_].*?\\d)\\w(\\w|[!@#$%]){4,20}",
            message = "{InvalidPassword}")
    @FormParam("password")
    private String password;

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
