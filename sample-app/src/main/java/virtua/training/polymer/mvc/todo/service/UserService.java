package virtua.training.polymer.mvc.todo.service;

import virtua.training.polymer.mvc.todo.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private User systemUser;

    public UserService() {
        activeUsers = new ArrayList<>();
        users = new ConcurrentHashMap<>();
        systemUser = add("System", "password1");
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

    public User add(String userId, String password) {
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

    public User getSystemUser() {
        return systemUser;
    }
}
