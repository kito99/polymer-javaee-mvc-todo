package virtua.training.polymer.mvc.todo.util;

import javax.inject.Named;
import javax.mvc.annotation.RedirectScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class encapsulates messages displayed to the users. There can be a
 * single info message and multiple error messages. Controllers can use this
 * class to queue messages for rendering. The class shows how named CDI beans
 * can be used as a model for the view. Please note that this class
 * uses the redirect scope to preserve messages across redirects.
 * <p>
 * This class taken from Christian Kaltepoth's TodoMVC app: https://github.com/chkal/todo-mvc.
 */
@Named
@RedirectScoped
public class Messages implements Serializable {

    private static final long serialVersionUID = 6012270416224546642L;

    private String info;

    private final List<String> errors = new ArrayList<>();

    public Messages addError(String error) {
        errors.add(error);
        return this;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
