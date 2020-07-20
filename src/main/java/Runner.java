import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.TwitterRequestHandler;

public class Runner extends Application<InputConfiguration>{
    public static void main(String[] args) throws Exception{
        new Runner().run(args);
    }


    @Override
    public void initialize(Bootstrap<InputConfiguration> bootstrap){

    }

    @Override
    public void run(InputConfiguration config, Environment environment){
        final TwitterRequestHandler resource = new TwitterRequestHandler();
        environment.jersey().register(resource);
    }
}
