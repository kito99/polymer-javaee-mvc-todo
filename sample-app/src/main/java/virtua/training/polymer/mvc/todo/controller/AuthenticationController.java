package virtua.training.polymer.mvc.todo.controller;

import virtua.training.polymer.mvc.todo.model.User;
import virtua.training.polymer.mvc.todo.service.UserService;

import javax.inject.Inject;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Handles login/logout duties.
 * <p>
 * Created: 21 Oct 2015
 *
 * @author Kito D. Mann
 */
@Path("/")
public class AuthenticationController {
    private final static Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private HttpSession session;

    @Inject
    private User user;

    /**
     * Performs a login and adds the user to the session. If the user doesn't exist, an account is automatically created.
     */
    @Path("login")
    @Controller
    @POST
    @CsrfValid
    // TODO: Add Bean Validation constraints
    public Response login(@FormParam("userId") String userId, @FormParam("password") String password) {
        if (!userService.userExists(userId)) {
            userService.add(userId, password);
        }
        Optional<User> loginResult = userService.login(userId, password);
        if (loginResult.isPresent()) {
            session.setAttribute("user", loginResult.get());
            return Response.temporaryRedirect(URI.create("../todo.xhtml")).build();
        }
        // TODO: Add error message
        return Response.temporaryRedirect(URI.create("../login.jsp")).build();
    }

    @Path("logout")
    @Controller
    @GET
    public String logout() {
        if (user != null) {
            userService.logout(user);
            session.removeAttribute("user");
        }
        return "/login.jsp";
    }
}
