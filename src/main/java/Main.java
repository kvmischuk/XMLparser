
import model.input.Log;
import model.output.Logday;
import model.output.UserEntry;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try {
            Handler handler = new Handler();
            List<Log> logs = handler.unmarshal(new File("/home/soli/IdeaProjects/XMLparser/src/main/resources/test1.xml"));
            System.out.println(logs);
            List<Logday> logdays = handler.convert(logs);
            System.out.println(logdays);

        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }


}
