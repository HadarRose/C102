package bootcamp;

import bootcamp.configuration.ConfigurationModule;
import bootcamp.resources.TwitterRequestResource;
import dagger.Component;

@Component(modules = {ConfigurationModule.class})
public interface TwitterResourceComponent {
    TwitterRequestResource twitterRequestResource();
}
