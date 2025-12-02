package xgbt95.domparse.hu;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XGBT95DOMRead {

    public static void main(String[] args) {
        try {

            // XML dokumentum betöltése
            File xmlFile = new File("XGBT95_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Kiírás bufferbe is mentéshez
            StringBuilder output = new StringBuilder();

            NodeList allNodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < allNodes.getLength(); i++) {
                Node node = allNodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    output.append("\n=== ").append(elem.getTagName()).append(" ===\n");

                    // Attribútumok kiírása
                    NamedNodeMap attrs = elem.getAttributes();
                    for (int a = 0; a < attrs.getLength(); a++) {
                        Node attr = attrs.item(a);
                        output.append(attr.getNodeName())
                              .append(" = ")
                              .append(attr.getNodeValue())
                              .append("\n");
                    }

                    // Gyerek elemek kiírása
                    NodeList children = elem.getChildNodes();
                    for (int c = 0; c < children.getLength(); c++) {
                        Node child = children.item(c);
                        if (child.getNodeType() == Node.ELEMENT_NODE) {
                            output.append("  ")
                                  .append(child.getNodeName())
                                  .append(": ")
                                  .append(child.getTextContent())
                                  .append("\n");
                        }
                    }
                }
            }

            // Konzolra kiírás
            System.out.println(output);

            // Fájlba mentés
            FileWriter fw = new FileWriter("XGBT95_read_output.txt");
            fw.write(output.toString());
            fw.close();

            System.out.println("Kiírás elmentve: XGBT95_read_output.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}