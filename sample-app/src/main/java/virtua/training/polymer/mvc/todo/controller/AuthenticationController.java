package virtua.training.polymer.mvc.todo.controller;

import virtua.training.polymer.mvc.todo.model.User;
import virtua.training.polymer.mvc.todo.service.UserService;
import virtua.training.polymer.mvc.todo.util.Messages;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.mvc.annotation.RedirectScoped;
import javax.mvc.annotation.View;
import javax.mvc.binding.BindingResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.Serializable;
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
@Controller
@RedirectScoped
public class AuthenticationController implements Serializable {
    private final static Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    private UserService userService;

    @Inject
    private HttpServletRequest request;

    @Inject
    private HttpSession session;

    @Inject
    private Models models;

    @Inject
    private User user;

    @Inject
    BindingResult bindingResult;

    @Inject
    Messages messages;

    /**
     * Performs a login using form parameters and adds the user to the session. Performs no validation.
     */
    @Path("login-no-validation")
    @POST
    @CsrfValid
    public String login(@FormParam("userId") String userId, @FormParam("password") String password) {
        Optional<User> loginResult = userService.login(userId, password);
        if (loginResult.isPresent()) {
            session.setAttribute("user", loginResult.get());
            return "redirect:../todo.xhtml";
            // Equivalent to:
            // Response.temporaryRedirect(URI.create("../todo.xhtml")).build();
        }
        models.put("userId", userId);
        models.put("loginError", true);
        return "/login.jsp";
        // Equivalent to:
        // Viewable view = new Viewable("../login.jsp");
        //Response.temporaryRedirect(URI.create("../login.jsp")).build();
    }

    /**
     * Performs a login using form parameters and adds the user to the session.
     * Uses BindingResults and bean validation.
     */
    @Path("login-validation")
    @POST
    @CsrfValid
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String login(@BeanParam @Valid LoginForm loginForm) {

        if (bindingResult.isFailed()) {
            bindingResult.getAllMessages().stream()
                    .forEach(messages::addError);
        } else {
            Optional<User> loginResult = userService.login(loginForm.getUserId(), loginForm.getPassword());
            if (loginResult.isPresent()) {
                session.setAttribute("user", loginResult.get());
                return "redirect:../todo.xhtml";

            } else {
                models.put("loginError", true);
            }
        }
        models.put("userId", loginForm.getUserId());
        return "/login.jsp";
    }

    @Path("logout")
    @POST
    public String logout() {
        if (user != null) {
            userService.logout(user);
            session.removeAttribute("user");
        }
        return "/login.jsp";
    }

}
