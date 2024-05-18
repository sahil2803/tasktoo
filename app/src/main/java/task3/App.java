/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package task3;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("data.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("record");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String postalZip = element.getElementsByTagName("postalZip").item(0).getTextContent();
                    String region = element.getElementsByTagName("region").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    String address = element.getElementsByTagName("address").item(0).getTextContent();
                    String list = element.getElementsByTagName("list").item(0).getTextContent();

                    System.out.println("Name: " + name);
                    System.out.println("Postal/Zip: " + postalZip);
                    System.out.println("Region: " + region);
                    System.out.println("Country: " + country);
                    System.out.println("Address: " + address);
                    System.out.println("List: " + list);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
