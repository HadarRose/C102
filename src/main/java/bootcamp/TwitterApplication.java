package bootcamp;

import bootcamp.configuration.ApplicationConfiguration;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TwitterApplication extends Application<ApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new TwitterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap) {}

    @Override
    public void run(ApplicationConfiguration config, Environment environment) {
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "http://localhost:9000"); // sets localhost:9000 as authorized requester
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*"); // url pattern to be added after any dispatcher type

        TwitterResourceComponent comp = DaggerTwitterResourceComponent.builder()
                .configurationModule(new ConfigurationModule(config))
                .build();
        environment.jersey().register(comp.twitterRequestResource());
    }
}
