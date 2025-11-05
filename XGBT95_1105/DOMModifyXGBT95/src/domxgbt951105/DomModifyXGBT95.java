package domxgbt951105;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModifyXGBT95 {
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
        File xmlFile = new File("XGBT95hallgato.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();

        Document xgbt95 = dBuilder.parse(xmlFile);
        xgbt95.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + xgbt95.getDocumentElement().getNodeName());

        NodeList nList = xgbt95.getElementsByTagName("hallgato");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) nNode;

                String hid = elem.getAttribute("id");

                Node keresztNode = elem.getElementsByTagName("keresztnev").item(0);
                Node vezetekNode = elem.getElementsByTagName("vezeteknev").item(0);

                if ("01".equals(hid)) {
                    keresztNode.setTextContent("Ádám");
                    vezetekNode.setTextContent("Kovács");
                    System.out.println("\nA(z) id=01 hallgató neve módosítva lett!");
                }

                String kname = keresztNode.getTextContent();
                String vname = vezetekNode.getTextContent();
                String fname = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();

                System.out.println("\nHallgató ID: " + hid);
                System.out.println("Keresztnév: " + kname);
                System.out.println("Vezetéknév: " + vname);
                System.out.println("Foglalkozás: " + fname);
            }
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(xgbt95);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}