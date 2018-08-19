package model.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UserEntry {

    private String userId;

    private String url;

    private int average;

    private int total;

    private int count = 0;

    public String getUserId() {
        return userId;
    }


    public String getUrl() {
        return url;
    }

    public int getAverage() {
        return average;
    }


    public UserEntry(String userId, String url, int total) {
        this.userId = userId;
        this.url = url;
        this.total = total;
        count++;
        average = total;
    }

    public void updateEntry(int seconds) {
        count++;
        total+=seconds;
        average = total/count;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", average=" + average +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntry entry = (UserEntry) o;
        return Objects.equals(userId, entry.userId) &&
                Objects.equals(url, entry.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, url);
    }
}
