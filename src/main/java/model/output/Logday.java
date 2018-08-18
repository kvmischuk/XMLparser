package model.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logday")
public class Logday {

    private LocalDate date;

    @XmlElement(name = "user", type = UserEntry.class)
    private List<UserEntry> userEntries;

}

