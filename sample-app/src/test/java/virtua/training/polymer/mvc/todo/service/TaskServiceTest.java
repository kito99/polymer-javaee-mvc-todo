package virtua.training.polymer.mvc.todo.service;

import org.junit.Before;
import org.junit.Test;
import virtua.training.polymer.mvc.todo.model.Task;
import virtua.training.polymer.mvc.todo.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link TaskService}.
 * <p>
 * Created: 23 Oct 2015
 *
 * @author Kito D. Mann
 */
public class TaskServiceTest {

    private TaskService taskService;
    private User user;

    @Before
    public void setUp() throws Exception {
        taskService = new TaskService();
        taskService.userService = new UserService();
        taskService.init();
        user = new User("user", "password");
    }

    @Test
    public void add() {
        List<Task> tasks = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Task task = new Task(String.valueOf(i), user.getUserId(), "task" + i, false);
            tasks.add(task);
            taskService.add(task);
        }
        Arrays.stream(taskService.getTasks()).forEach(task -> tasks.remove(task));
        assertTrue("All tasks weren't added", tasks.isEmpty());
    }

    @Test
    public void update() {
        taskService.removeAll();
        List<Task> tasks = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Task task = new Task(String.valueOf(i), user.getUserId(), "task" + i, false);
            tasks.add(task);
            taskService.add(task);
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            task.setName("updated task" + i);
            taskService.update(task);
        }
        Arrays.stream(taskService.getTasks()).forEach(task -> assertTrue("Wrong task name: " + task.getName(),
                task.getName().startsWith("updated task")));
    }

    @Test
    public void remove() {
        List<Task> tasks = new ArrayList<>();
        for (long i = 0; i < 10; i++) {
            Task task = new Task(String.valueOf(i), user.getUserId(), "task" + i, false);
            tasks.add(task);
            taskService.add(task);
        }
        Task taskToKill = tasks.get(5);
        assertTrue(taskService.get(taskToKill.getId()).isPresent());
        taskService.remove(taskToKill.getId());
        assertFalse(taskService.get(taskToKill.getId()).isPresent());
    }
}