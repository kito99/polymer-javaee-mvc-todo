package virtua.training.polymer.mvc.todo;

import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

/**
 * Application instance for this web app. Provides configuration info and alleviates the need to define a servlet in
 * web.xml.
 *
 * Created: 21 Oct 2015
 * @author Kito D. Mann
 */
@ApplicationPath("/resources/*")
public class TodoApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();

        /*
         * Enables CSRF protection (default is OFF). If enabled, you will have to add an
         * hidden input to your forms which will contains the required token.
         */
        properties.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.IMPLICIT);

        return properties;
    }
}