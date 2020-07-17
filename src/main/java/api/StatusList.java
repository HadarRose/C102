package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import twitter4j.Status;

import java.util.List;

public class StatusList {
    private List<Status> statusList;

    public StatusList(){}

    public StatusList(List<Status> statusList){
        this.statusList = statusList;
    }

    @JsonProperty
    public List<Status> getStatusList(){
        return statusList;
    }

}
