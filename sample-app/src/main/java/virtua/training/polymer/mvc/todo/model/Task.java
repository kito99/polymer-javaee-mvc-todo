package virtua.training.polymer.mvc.todo.model;

/**
 * Represents a task.
 * <p>
 * Created: 23 Oct 2015
 *
 * @author Kito D. Mann
 */
public class Task {

    private String userId;

    private String id;

    private String name;

    private boolean completed;


    public Task() {
    }

    public Task(String id, String userId, String name, boolean completed) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.completed = completed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
