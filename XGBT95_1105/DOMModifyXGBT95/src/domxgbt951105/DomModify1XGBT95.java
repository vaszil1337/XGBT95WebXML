package domxgbt951105;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModify1XGBT95 {
    public static void main(String[] args)
            throws SAXException, IOException, ParserConfigurationException, TransformerException {

        File xmlFile = new File("orarendXGBT95.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());

        NodeList oraList = doc.getElementsByTagName("ora");
        if (oraList.getLength() > 0) {
            Element firstOra = (Element) oraList.item(0);

            Element newElem = doc.createElement("oraado");
            newElem.setTextContent("Dr. Kiss Gábor");
            firstOra.appendChild(newElem);

            System.out.println("\nÚj 'oraado' elem hozzáadva az első 'ora' elemhez.");
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(doc);

        System.out.println("\n--- MÓDOSÍTOTT XML KIÍRÁSA ---\n");
        transformer.transform(source, new StreamResult(System.out));

        StreamResult fileResult = new StreamResult(new File("orarendModify1XGBT95.xml"));
        transformer.transform(source, fileResult);

        System.out.println("\nA módosított XML fájl mentve: orarendModify1XGBT95.xml");

        for (int i = 0; i < oraList.getLength(); i++) {
            Element oraElem = (Element) oraList.item(i);
            String tipus = oraElem.getAttribute("tipus");
            if (tipus.equalsIgnoreCase("gyakorlat")) {
                oraElem.setAttribute("tipus", "előadás");
            }
        }

        System.out.println("\n--- Minden 'tipus' módosítva gyakorlatról előadásra ---\n");

        printStructured(doc.getDocumentElement(), 0);
    }

    private static void printStructured(Node node, int indent) {
        for (int i = 0; i < indent; i++) System.out.print("  ");
        System.out.print("<" + node.getNodeName());

        if (node.hasAttributes()) {
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Node attr = attrs.item(i);
                System.out.print(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
            }
        }
        System.out.print(">");

        NodeList children = node.getChildNodes();
        boolean hasElements = false;

        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                hasElements = true;
                System.out.println();
                printStructured(child, indent + 1);
            } else if (child.getNodeType() == Node.TEXT_NODE) {
                String text = child.getTextContent().trim();
                if (!text.isEmpty()) System.out.print(text);
            }
        }

        if (hasElements) {
            for (int i = 0; i < indent; i++) System.out.print("  ");
        }

        System.out.println("</" + node.getNodeName() + ">");
    }
}