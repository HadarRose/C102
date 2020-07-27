package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import java.util.List;

public class StatusList {
    private static Logger logger = LoggerFactory.getLogger(StatusList.class);

    private List<Status> statusList;

    /**
     * Constructor. Doesn't set up properties to anything.
     */
    public StatusList() {

    }

    /**
     * Constructor.
     *
     * @param statusList value for statusList property
     */
    public StatusList(List<Status> statusList) {
        logger.debug("StatusList created with statusList = {}", statusList.toString());
        this.statusList = statusList;
    }

    /**
     * @return List<Status>. The object's statusList property.
     */
    @JsonProperty
    public List<Status> getStatusList() {
        return statusList;
    }

}
