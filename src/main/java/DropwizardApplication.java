import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.DropwizardResource;
//import resources.TimelineResource;
import twitter4j.api.TimelinesResources;

//public class DropwizardApplication extends Application<DropwizardConfig>{
public class DropwizardApplication extends Application<DropwizardConfig>{
    public static void main(String[] args) throws Exception{
        new DropwizardApplication().run(args);
    }

    // TODO: def need to change these overrides?
    @Override
    public String getName(){ // TODO: what is this for?
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfig> bootstrap){

    }

    @Override
    public void run(DropwizardConfig config, Environment environment){
        final DropwizardResource resource = new DropwizardResource();
        environment.jersey().register(resource);
    }
}
