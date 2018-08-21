
import model.input.Log;
import model.output.Logday;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("/home/soli/IdeaProjects/XMLparser/src/main/resources/directory.properties"));
        File inputDirectory = new File(properties.getProperty("inputDirectory"));
        File outputDirectory = new File(properties.getProperty("outputDirectory"));
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (File file : Objects.requireNonNull(inputDirectory.listFiles())) {
            if(!file.getName().endsWith(".xml")) {
                continue;
            }
            executorService.submit(() -> {
                try {
                    List<Log> logs = Handler.unmarshal(file);
                    List<Logday> logdays = Handler.convert(logs);
                    String name = "avg_" + file.getName();
                    File outputFile = new File(outputDirectory.getAbsolutePath() + "/" + name);
                    outputFile.createNewFile();
                    Handler.marshal(logdays, outputFile);
                } catch (JAXBException | IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}



