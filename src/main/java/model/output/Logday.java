package model.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "logday")
public class Logday {

    @XmlElement(name = "date")
    private String date;

    @XmlElement(name = "user", type = UserEntry.class)
    private List<UserEntry> userEntries = new ArrayList<>();

    public Logday() {
    }

    public Logday(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public List<UserEntry> getUserEntries() {
        return userEntries;
    }

    public void updateEntries(UserEntry entry) {
        for(UserEntry e : userEntries) {
            if (e.equals(entry)) {
                e.updateEntry(entry.getAverage());
                return;
            }
        }
        userEntries.add(entry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logday logday = (Logday) o;
        return Objects.equals(date, logday.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return "Logday{" +
                "date=" + date +
                ", userEntries=" + userEntries +
                '}';
    }
}

