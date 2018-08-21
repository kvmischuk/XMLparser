package model.input;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "input")
public class Input {

    @XmlElement(name = "log", type = Log.class)
    private List<Log> logs = new ArrayList<>();

    public Input() {}

    public Input(List<Log> logs) {
        this.logs = logs;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }


}
