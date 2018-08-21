package model.output;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "output")
public class Output {

    @XmlElement(name = "logday", type = Logday.class)
    private List<Logday> logdayList = new ArrayList<>();

    public Output() {
    }

    public Output(List<Logday> logdayList) {
        this.logdayList = logdayList;
    }

    public List<Logday> getLogdayList() {
        return logdayList;
    }

    public void setLogdayList(List<Logday> logdayList) {
        this.logdayList = logdayList;
    }
}
