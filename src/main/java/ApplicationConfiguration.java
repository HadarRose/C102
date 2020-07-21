// This class exists because dropwizard application's .run() function is dependant on having a config class
// but in reality, we don't have anything to configure atm

import io.dropwizard.Configuration;

public class ApplicationConfiguration extends Configuration {

}