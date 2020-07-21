import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TwitterRequestResource;

public class TwitterApplication extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {

    }

    @Override
    public void run(ApplicationConfiguration config, Environment environment) {
        final TwitterRequestResource resource = new TwitterRequestResource();
        environment.jersey().register(resource);
    }
}
