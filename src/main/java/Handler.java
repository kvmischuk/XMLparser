import model.input.Input;
import model.input.Log;
import model.output.Logday;
import model.output.Output;
import model.output.UserEntry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Handler {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public static List<Log> unmarshal(File file) throws JAXBException {

        JAXBContext context = JAXBContext.newInstance(Input.class);
        Unmarshaller um = context.createUnmarshaller();
        Input input = (Input) um.unmarshal(file);

        return input.getLogs();
    }

    public static void marshal(List<Logday> logdays, File file) throws IOException, JAXBException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        JAXBContext context = JAXBContext.newInstance(Output.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(new Output(logdays), writer);
        writer.close();
    }

    public static List<Logday> convert(List<Log> logs) {
        List<Logday> logdays = new ArrayList<>();
        for (Log log : logs) {
            Instant instant = Instant.ofEpochSecond(log.getTimestamp());
            String date = dateTimeFormatter.format(LocalDate.ofInstant(instant,ZoneOffset.UTC));
            Instant instant1 = instant.plusSeconds(log.getSeconds());
            String date1 = dateTimeFormatter.format(LocalDate.ofInstant(instant1,ZoneOffset.UTC));
            if (date1.equals(date)) {
                UserEntry entry = new UserEntry(log.getUserId(), log.getUrl(), log.getSeconds());
                Logday logday = new Logday(date);
                updateLogdays(logdays, logday, entry);
            } else {
                long sec = (instant1.toEpochMilli() - LocalDate.ofInstant(instant1,ZoneOffset.UTC).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli())/1000;
                UserEntry entry = new UserEntry(log.getUserId(),log.getUrl(), (int) (log.getSeconds()-sec));
                UserEntry entry1 = new UserEntry(log.getUserId(),log.getUrl(),(int) sec);
                Logday logday = new Logday(date);
                Logday logday1 = new Logday(date1);
                updateLogdays(logdays,logday,entry);
                updateLogdays(logdays,logday1,entry1);
            }
        }
        logdays.sort(Comparator.comparing(logday -> LocalDate.parse(logday.getDate(), dateTimeFormatter)));
        for (Logday l : logdays) {
            l.getUserEntries().sort(Comparator.comparing(u -> Integer.parseInt(u.getUserId().substring(4))));
        }
        return logdays;
    }

    public static void updateLogdays(List<Logday> logdays, Logday logday, UserEntry entry) {
        for (Logday l : logdays) {
            if (l.equals(logday)) {
                l.updateEntries(entry);
                return;
            }
        }
        logday.updateEntries(entry);
        logdays.add(logday);
    }
}

