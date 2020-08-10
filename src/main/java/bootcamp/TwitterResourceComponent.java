package bootcamp;

import bootcamp.configuration.ConfigurationModule;
import bootcamp.resources.TwitterRequestResource;
import bootcamp.services.twitter4j.TwitterResourceService;
import dagger.Component;

@Component(modules = {TwitterResourceService.class, ConfigurationModule.class})
public interface TwitterResourceComponent {
    TwitterRequestResource twitterRequestResource();
}
