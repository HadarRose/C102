import configuration.Configuration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TwitterRequestResource;

public class TwitterApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    @Override
    public void run(Configuration config, Environment environment) {
        final TwitterRequestResource resource = new TwitterRequestResource();
        environment.jersey().register(resource);
    }
}
