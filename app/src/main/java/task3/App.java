package task3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Specify the path to the XML file
            String filePath = "data.xml";

            // Parse XML file
            File xmlFile = new File(filePath);
            if (!xmlFile.exists() || !xmlFile.isFile()) {
                System.out.println("The specified XML file does not exist or is not a file: " + filePath);
                return;
            }

            // Validate field numbers
            List<Integer> fieldNumbers = new ArrayList<>();
            for (String fieldNumber : args) {
                try {
                    int field = Integer.parseInt(fieldNumber);
                    if (field < 1 || field > 6) {
                        System.out.println("Invalid field number: " + fieldNumber);
                        return;
                    }
                    fieldNumbers.add(field);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid field number: " + fieldNumber);
                    return;
                }
            }

            if (fieldNumbers.isEmpty()) {
                System.out.println("No valid field numbers provided.");
                return;
            }

            // Initialize SAX parser
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            SAXHandler handler = new SAXHandler(fieldNumbers);
            xmlReader.setContentHandler(handler);

            // Parse the XML file
            xmlReader.parse(new InputSource(new FileInputStream(xmlFile)));

            // Get the JSON list from the handler
            List<ObjectNode> jsonList = handler.getJsonList();

            // Convert the list of JSON objects to JSON array and print
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonOutput = objectMapper.writeValueAsString(jsonList);
            System.out.println(jsonOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
