import model.input.Input;
import model.input.Log;
import model.output.Logday;
import model.output.Output;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JAXBXMLHandler {

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
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        m.marshal(new Output(logdays),writer);
        writer.close();
    }
}
