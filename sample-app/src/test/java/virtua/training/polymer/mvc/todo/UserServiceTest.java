package virtua.training.polymer.mvc.todo;

import org.junit.Before;
import org.junit.Test;
import virtua.training.polymer.mvc.todo.model.User;
import virtua.training.polymer.mvc.todo.service.UserService;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Tests for {@link UserService} class.
 *
 * Created: 23 Oct 2015
 *
 * @author Kito D. Mann
 */
public class UserServiceTest {

    private UserService service;

    @Before
    public void setUp() throws Exception {
        service = new UserService();
    }

    @Test
    public void login_Valid() {
        User user = service.add("foo", "bar");
        Optional<User> result = service.login("foo", "bar");
        assertTrue(result.isPresent());
        assertThat(user, equalTo(result.get()));
        assertTrue(service.getActiveUsers().contains(user));
        assertThat(user, equalTo(service.getUsers().get("foo")));
    }

    @Test
    public void login_Invalid() {
        User user = service.add("foo", "bar");
        Optional<User> result = service.login("foo", "barbaz");
        assertFalse(result.isPresent());
        assertFalse(service.getActiveUsers().contains(user));
    }

    @Test
    public void logout() {
        User user = service.add("foo", "bar");
        Optional<User> result = service.login("foo", "bar");
        assertTrue(result.isPresent());
        service.logout(user);
        assertFalse(service.getActiveUsers().contains(user));
        assertThat(user, equalTo(service.getUsers().get("foo")));
    }

    @Test
    public void logout_InvalidUser() {
        User user = new User("not a real", "user");
        service.logout(user);
        assertFalse(service.getActiveUsers().contains(user));
        assertFalse(service.userExists(user.getUserId()));
    }

    @Test
    public void logout_NeverLoggedIn() {
        User user = service.add("foo", "bar");
        service.logout(user);
        assertFalse(service.getActiveUsers().contains(user));
        assertThat(user, equalTo(service.getUsers().get("foo")));
    }
}