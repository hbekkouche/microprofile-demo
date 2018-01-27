package demo.microprofile.rest;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;


@Path("/metric-demo")
@ApplicationScoped
public class MetricsResource {

    @Inject
    private Counter aCounter;

    @PostConstruct
    public void startup() {
        aCounter.inc(42);
    }

    @GET
    @Produces("text/plain")
    //@Counted(description = "Counting of call", absolute = true, tags = {"type=counter"})
    public Response doGet() {
        aCounter.inc();
        return Response.ok("Metrics! " + aCounter.getCount()).build();
    }

}
