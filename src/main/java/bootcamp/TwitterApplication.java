package bootcamp;

import bootcamp.configuration.ApplicationConfiguration;
import bootcamp.configuration.ConfigurationModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TwitterApplication extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {}

    @Override
    public void run(ApplicationConfiguration config, Environment environment) {
        TwitterResourceComponent comp = DaggerTwitterResourceComponent.builder()
                .configurationModule(new ConfigurationModule(config))
                .build();
        environment.jersey().register(comp.twitterRequestResource());
    }
}
