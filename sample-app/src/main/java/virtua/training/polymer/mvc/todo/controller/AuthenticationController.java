package virtua.training.polymer.mvc.todo.controller;

import virtua.training.polymer.mvc.todo.model.User;
import virtua.training.polymer.mvc.todo.service.UserService;
import virtua.training.polymer.mvc.todo.util.Messages;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.CsrfValid;
import javax.mvc.annotation.RedirectScoped;
import javax.mvc.binding.BindingResult;
import javax.servlet.http.HttpSession;
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
@RedirectScoped
public class AuthenticationController implements Serializable {
    private final static Logger logger = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    private UserService userService;

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
     * Performs a login and adds the user to the session. If the user doesn't exist, an account is automatically created.
     */
    @Path("login")
    @Controller
    @POST
    @CsrfValid
    public String login(@FormParam("userId") String userId, @FormParam("password") String password) {
        if (!userService.userExists(userId)) {
            userService.add(userId, password);
        }
        Optional<User> loginResult = userService.login(userId, password);
        if (loginResult.isPresent()) {
            session.setAttribute("user", loginResult.get());
            return "redirect:../todo.xhtml";
        }
        models.put("loginError", true);
        return "/login.jsp";
    }

    @Path("logout")
    @Controller
    @POST
    public String logout() {
        if (user != null) {
            userService.logout(user);
            session.removeAttribute("user");
        }
        return "/login.jsp";
    }

//    @Path("login")
//    @Controller
//    @POST
//    @CsrfValid
//    @ValidateOnExecution(type = ExecutableType.NONE)
//    public String login(@BeanParam @Valid User user) {
//
//        if (bindingResult.isFailed()) {
//            bindingResult.getAllMessages().stream()
//                    .forEach(message -> messages.addError(message));
//        } else {
//            if (!userService.userExists(user.getUserId())) {
//                userService.add(user.getUserId(), user.getPassword());
//            }
//            Optional<User> loginResult = userService.login(user.getUserId(), user.getPassword());
//            if (loginResult.isPresent()) {
//                session.setAttribute("user", loginResult.get());
//                return "redirect:../todo.xhtml";
//                //Response.temporaryRedirect(URI.create("../todo.xhtml")).build();
//            } else {
//                models.put("loginError", true);
//            }
//        }
//        return "/login.jsp";
//        //Viewable view = new Viewable("../login.jsp");
//        //Response.temporaryRedirect(URI.create("../login.jsp")).build();
//    }


}
