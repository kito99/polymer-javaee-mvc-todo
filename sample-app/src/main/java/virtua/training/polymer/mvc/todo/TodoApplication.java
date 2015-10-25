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
@ApplicationPath("/resources/")
public class TodoApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();

        /*
         * Set CSRF protection (default is EXPLICIT). If you use IMPLICIT (or the @CsrfValid annotation on resource or
         * controller methods when it is set to EXPLICIT), you will have to add an
         * hidden input to your forms which will contains the required token.
         *
         * NOTE: Currently (10/25/2015) in Ozark, setting this to IMPLICIT also enabled CSRF protection for JSON requests, which
         * may not be what you want. See: https://java.net/jira/browse/OZARK-64.
         */
        properties.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        return properties;
    }
}