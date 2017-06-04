import au.com.bytecode.opencsv.CSVReader;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

/**
 * Created by maxim.ovechkin on 04.06.2017.
 * home work 1.1
 */
public class Application {
    public static void main(String[] args) {
        for (String line:readCSV("pom.xml")) {
            System.out.println(line);
        };
    }

    public static List<String> readCSV(String path) {
        try {
            System.out.println("Reading from: " + path);

            CSVReader reader = new CSVReader(new FileReader(path));
            return reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
