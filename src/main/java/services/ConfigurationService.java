package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.ApplicationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ConfigurationService {

    /**
     * Constructor
     */
    private static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    public ConfigurationService() {
        logger.info("New ConfigurationService");
    }

    /**
     * Loads twitter credential keys from file specified by path property
     */
    public ApplicationConfiguration getKeys() throws IOException {
        logger.info("ConfigurationService called getKeys()");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File("./config.yml"), ApplicationConfiguration.class);
        } catch (IOException e) {
            logger.error("Exception was thrown: {}.", e.getMessage(), e);
            throw e;
        }
    }
}
