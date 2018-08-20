
import model.input.Log;
import model.output.Logday;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try {
            Handler handler = new Handler();
            List<Log> logs = handler.unmarshal(new File("/home/soli/IdeaProjects/XMLparser/src/main/resources/test.xml"));
            System.out.println(logs);
            List<Logday> logdays = handler.convert(logs);
            System.out.println(logdays);
            handler.marshal(logdays,new File("/home/soli/IdeaProjects/XMLparser/src/main/resources/output.xml"));

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }


    }


}
