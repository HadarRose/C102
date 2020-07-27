import configuration.ApplicationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TwitterRequestResource;

import java.io.IOException;

public class TwitterApplication extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {

    }

    @Override
    public void run(ApplicationConfiguration config, Environment environment) throws IOException {
        final TwitterRequestResource resource = new TwitterRequestResource();
        environment.jersey().register(resource);
    }
}
