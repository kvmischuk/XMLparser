
import model.input.Log;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            List<Log> logs = JAXBXMLHandler.unmarshal(new File("/home/soli/IdeaProjects/XMLparser/src/main/resources/test.xml"));
            System.out.println(logs);
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }
}
