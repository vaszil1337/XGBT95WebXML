package xgbt95.domparse.hu;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class XGBT95DOMModify {

    public static void main(String[] args) {
        try {

            File xmlFile = new File("XGBT95_XML.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Node gyoker = doc.getDocumentElement();

            // ---------- 1. módosítás: hotel csillagszám ----------
            Element hotel1 = (Element) doc.getElementsByTagName("hotel").item(0);
            hotel1.getElementsByTagName("csillag").item(0).setTextContent("3");
            System.out.println("Hotel #1 csillagszáma 3-ra módosítva.");

            // ---------- 2. módosítás: új szolgáltatás ----------
            Element newServ = doc.createElement("szolgaltatas");
            newServ.setAttribute("szolg_id", "3");
            newServ.setAttribute("h_id", "1");

            Element nev = doc.createElement("nev");
            nev.setTextContent("Parkolás");
            Element ar = doc.createElement("ar");
            ar.setTextContent("2500");

            newServ.appendChild(nev);
            newServ.appendChild(ar);

            Comment komment = doc.createComment(" Szolgáltatás 3 ");
            Text uresSor = doc.createTextNode("\n    ");

            NodeList szolgList = doc.getElementsByTagName("szolgaltatas");
            Node uSzolg = szolgList.item(szolgList.getLength() - 1);
            Node kovNode = uSzolg.getNextSibling();
            gyoker.insertBefore(uresSor, kovNode);
            gyoker.insertBefore(komment, kovNode);
            gyoker.insertBefore(uresSor.cloneNode(false), kovNode);
            gyoker.insertBefore(newServ, kovNode);

            System.out.println("Új szolgáltatás hozzáadva (Parkolás).");

            // ---------- 3. módosítás: vendég email csere ----------
            Element vendeg1 = (Element) doc.getElementsByTagName("vendeg").item(0);
            vendeg1.getElementsByTagName("email").item(0).setTextContent("uj.email@example.com");
            System.out.println("Vendég #1 email címe módosítva.");

            // ---------- 4. módosítás: foglalás állapot ----------
            Element foglalas1 = (Element) doc.getElementsByTagName("foglalas").item(0);
            Element allapot = (Element) foglalas1.getElementsByTagName("allapot").item(0);
            allapot.getElementsByTagName("lemondva").item(0).setTextContent("true");
            allapot.getElementsByTagName("jovahagyva").item(0).setTextContent("false");
            System.out.println("Foglalás #1 lemondva értéke true-ra állítva.");

            // ---------- módosított XML kiírása konzolra ----------
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(System.out);

            System.out.println("\n========== Módosított XML dokumentum ==========\n");
            transformer.transform(source, result);
            System.out.println("\n\n========================================");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}