import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TwitterRequestHandler;

public class TwitterApplication extends Application<ApplicationConfiguration>{
    public static void main(String[] args) throws Exception{
        new TwitterApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<ApplicationConfiguration> bootstrap){

    }

    @Override
    public void run(ApplicationConfiguration config, Environment environment){
        final TwitterRequestHandler resource = new TwitterRequestHandler();
        environment.jersey().register(resource);
    }
}
