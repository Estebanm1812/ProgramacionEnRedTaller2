package config;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> res = new HashSet<>();
        res.add(services.UserServices.class);
        res.add(services.ProductService.class);
        res.add(services.OrderServices.class);
        return res;
    }
}
