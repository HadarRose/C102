package resources;

import api.Post;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/hello-world") // TODO: change this
@Produces(MediaType.APPLICATION_JSON) // TODO: learn about this
public class DropwizardResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter; // used to generate IDs

    public DropwizardResource(String template, String defaultName){
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Post sayHello(@QueryParam("name") Optional<String> name){
        final String value = String.format(template, name.orElse(defaultName));
        return new Post(counter.incrementAndGet(), value);
    }
}
