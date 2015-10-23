package virtua.training.polymer.mvc.todo;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import virtua.training.polymer.mvc.todo.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple service for managing users and active users (in memory).
 *
 * Created: 22 Oct 2015
 *
 * @author Kito D. Mann
 */
@ApplicationScoped
public class UserService {

    private List<User> activeUsers;
    private Map<String, User> users;

    public UserService() {
        activeUsers = new ArrayList<>();
        users = new ConcurrentHashMap<>();
    }

    public Optional<User> login(String userId, String password) {
        User user = getUsers().get(userId);
        if (user != null && user.getPassword().equals(password)) {
            getActiveUsers().add(user);
            getUsers().put(userId, user);
        } else {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    public User addUser(String userId, String password) {
        User user = new User(userId, password);
        getUsers().put(userId, user);
        return user;
    }

    public List<User> getActiveUsers() {
        return activeUsers;

    }
    public boolean userExists(String userId) {
        return getUsers().containsKey(userId);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void logout(User user) {
        getActiveUsers().remove(user);
    }

}
