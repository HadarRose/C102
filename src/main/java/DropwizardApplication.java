import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.DropwizardResource;

public class DropwizardApplication extends Application<DropwizardConfig>{
    public static void main(String[] args) throws Exception{
        new DropwizardApplication().run(args);
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
