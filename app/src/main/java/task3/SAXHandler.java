package task3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler {

    private final List<ObjectNode> jsonList = new ArrayList<>();
    private final List<Integer> fieldNumbers;
    private ObjectNode currentRecord;
    private StringBuilder content;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SAXHandler(List<Integer> fieldNumbers) {
        this.fieldNumbers = fieldNumbers;
    }

    public List<ObjectNode> getJsonList() {
        return jsonList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("record")) {
            currentRecord = objectMapper.createObjectNode();
        }
        content = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentRecord != null) {
            switch (qName.toLowerCase()) {
                case "name":
                    if (fieldNumbers.contains(1)) {
                        currentRecord.put("Name", content.toString());
                    }
                    break;
                case "postalzip":
                    if (fieldNumbers.contains(2)) {
                        currentRecord.put("Postal/Zip", content.toString());
                    }
                    break;
                case "region":
                    if (fieldNumbers.contains(3)) {
                        currentRecord.put("Region", content.toString());
                    }
                    break;
                case "country":
                    if (fieldNumbers.contains(4)) {
                        currentRecord.put("Country", content.toString());
                    }
                    break;
                case "address":
                    if (fieldNumbers.contains(5)) {
                        currentRecord.put("Address", content.toString());
                    }
                    break;
                case "list":
                    if (fieldNumbers.contains(6)) {
                        currentRecord.put("List", content.toString());
                    }
                    break;
                case "record":
                    jsonList.add(currentRecord);
                    currentRecord = null;
                    break;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }
}
