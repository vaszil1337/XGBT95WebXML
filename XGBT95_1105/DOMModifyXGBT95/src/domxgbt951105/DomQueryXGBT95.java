package domxgbt951105;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomQueryXGBT95 {
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {

        File xmlFile = new File("XGBT95hallgato.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
        System.out.println("--------------------------");

        NodeList nList = doc.getElementsByTagName("hallgato");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                Node vNode = elem.getElementsByTagName("vezeteknev").item(0);
                String vezeteknev = vNode.getTextContent();

                System.out.println("Aktuális elem: \n" + elem.getNodeName());
                System.out.println("vezeteknev: " + vezeteknev);
                System.out.println();
            }
        }
    }
}