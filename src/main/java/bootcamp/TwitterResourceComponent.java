package bootcamp;

//import bootcamp.configuration.ApplicationConfiguration;
import bootcamp.configuration.ConfigurationModule;
import bootcamp.resources.TwitterRequestResource;
import bootcamp.services.twitter4j.TwitterResourceService;
import dagger.Component;

import javax.inject.Singleton;

// todo do i need singleton?
@Singleton
@Component(modules = {TwitterResourceService.class, ConfigurationModule.class})
//@Component(modules = {TwitterResourceService.class})
public interface TwitterResourceComponent {

    /* todo objects that Resource needs
        - TwitterResourceService
        - TwitterKeys (for TwitterResourceService)
        - TwitterExceptionService (TBD)
    * */
    TwitterRequestResource twitterRequestResource();
}
