package xgbt95.domparse.hu;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class XGBT95DOMQuery {

    public static void main(String[] args) {
        try {

            File xmlFile = new File("XGBT95_XML.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // ---------------- 1. LEKÉRDEZÉS ----------------
            System.out.println("1. Hotelek neve és városa:");
            NodeList hotels = doc.getElementsByTagName("hotel");

            for (int i = 0; i < hotels.getLength(); i++) {
                Element h = (Element) hotels.item(i);
                String nev = h.getElementsByTagName("nev").item(0).getTextContent();
                String varos = h.getElementsByTagName("varos").item(0).getTextContent();

                System.out.println("- " + nev + " (" + varos + ")");
            }

            // ---------------- 2. LEKÉRDEZÉS ----------------
            System.out.println("\n2. 5 CSILLAGOS hotelek:");
            for (int i = 0; i < hotels.getLength(); i++) {
                Element h = (Element) hotels.item(i);
                if (h.getElementsByTagName("csillag").item(0).getTextContent().equals("5")) {
                    System.out.println("- " + h.getElementsByTagName("nev").item(0).getTextContent());
                }
            }

            // ---------------- 3. LEKÉRDEZÉS ----------------
            System.out.println("\n3. Vendégek nevei:");
            NodeList guests = doc.getElementsByTagName("vendeg");

            for (int i = 0; i < guests.getLength(); i++) {
                Element v = (Element) guests.item(i);
                System.out.println("- " + v.getElementsByTagName("nev").item(0).getTextContent());
            }

            // ---------------- 4. LEKÉRDEZÉS ----------------
            System.out.println("\n4. 3 vagy több férőhelyes szobák:");
            NodeList rooms = doc.getElementsByTagName("szoba");

            for (int i = 0; i < rooms.getLength(); i++) {
                Element s = (Element) rooms.item(i);
                int ferohely = Integer.parseInt(s.getElementsByTagName("ferohely").item(0).getTextContent());

                if (ferohely >= 3) {
                    System.out.println("- Szoba ID: " + s.getAttribute("s_id")
                                       + " (férőhely: " + ferohely + ")");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}