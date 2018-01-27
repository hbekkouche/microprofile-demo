package demo.microprofile.rest;

import demo.microprofile.model.Session;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/config")
@ApplicationScoped
public class ConfigResource {

    @Inject
    @ConfigProperty(name = "application.name")
    private String appName;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> get() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("application.name", appName);
        return map;
    }

}
