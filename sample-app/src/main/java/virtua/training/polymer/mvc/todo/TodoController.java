package virtua.training.polymer.mvc.todo;

import virtua.training.polymer.mvc.todo.model.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mvc.MvcContext;
import javax.mvc.annotation.Controller;
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
 * Primary controller -- handles all functionality in this simple app.
 * <p>
 * Created: 21 Oct 2015
 *
 * @author Kito D. Mann
 */
@Path("/")
public class TodoController {
    private final static Logger logger = Logger.getLogger(TodoController.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private HttpSession session;

    /**
     * Performs a login and adds the user to the session. If the user doesn't exist, an account is automatically created.
     */
    @Path("login")
    @Controller
    @POST
    // TODO: Add Bean Validation constraints
    public Response login(@FormParam("userId") String userId, @FormParam("password") String password) {
        if (!userService.userExists(userId)) {
            userService.addUser(userId, password);
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
    public String logout(User user) {
        userService.logout(user);
        session.removeAttribute("user");

        return "/login.jsp";
    }
}
