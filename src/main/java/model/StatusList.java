package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;

import java.util.List;

public class StatusList {
    private List<Status> statusList;

    private static Logger logger = LoggerFactory.getLogger(StatusList.class);

    /** Constructor. Doesn't set up properties to anything.
     * */
    public StatusList() {
        logger.debug("StatusList created with empty constructor.");
    }

    /** Constructor.
     * @param statusList value for statusList property
     * */
    public StatusList(List<Status> statusList) {
        logger.debug("StatusList created with statusList = {}", statusList.toString());
        this.statusList = statusList;
    }

    /** @return List<Status>. The object's statusList property.
     * */
    @JsonProperty
    public List<Status> getStatusList() {
        logger.debug("getStatusList will return {}", statusList.toString());
        return statusList;
    }

}
