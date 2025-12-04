package xgbt95.domparse.hu;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XGBT95DOMRead {

    public static void main(String[] args)
            throws Exception {

        File xmlFile = new File("XGBT95_XML.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        StringBuilder out = new StringBuilder();

        /* ============================
           SZOLGÁLTATÁSOK
        ============================ */
        NodeList szolgList = doc.getElementsByTagName("szolgaltatas");
        out.append("=== Szolgáltatások ===\n");

        for (int i = 0; i < szolgList.getLength(); i++) {
            Element e = (Element) szolgList.item(i);

            out.append("\nSzolgáltatás ID: ").append(e.getAttribute("szolg_id")).append("\n");
            out.append("Hotel ID: ").append(e.getAttribute("h_id")).append("\n");

            String nev = e.getElementsByTagName("nev").item(0).getTextContent();
            String ar = e.getElementsByTagName("ar").item(0).getTextContent();

            out.append("Név: ").append(nev).append("\n");
            out.append("Ár: ").append(ar).append(" Ft\n");
        }

        /* ============================
           HOTELEK
        ============================ */
        NodeList hotelList = doc.getElementsByTagName("hotel");
        out.append("\n\n=== Hotelek ===\n");

        for (int i = 0; i < hotelList.getLength(); i++) {
            Element e = (Element) hotelList.item(i);

            out.append("\nHotel ID: ").append(e.getAttribute("h_id")).append("\n");

            out.append("Név: ")
                .append(e.getElementsByTagName("nev").item(0).getTextContent()).append("\n");

            out.append("Város: ")
                .append(e.getElementsByTagName("varos").item(0).getTextContent()).append("\n");

            NodeList tels = e.getElementsByTagName("telefonszam");
            out.append("Telefonszámok:\n");
            for (int t = 0; t < tels.getLength(); t++) {
                out.append("  - ").append(tels.item(t).getTextContent()).append("\n");
            }

            out.append("Csillag: ")
                .append(e.getElementsByTagName("csillag").item(0).getTextContent())
                .append("\n");
        }

        /* ============================
           SZOBÁK
        ============================ */
        NodeList szobaList = doc.getElementsByTagName("szoba");
        out.append("\n\n=== Szobák ===\n");

        for (int i = 0; i < szobaList.getLength(); i++) {
            Element e = (Element) szobaList.item(i);

            out.append("\nSzoba ID: ").append(e.getAttribute("s_id")).append("\n");
            out.append("Hotel ID: ").append(e.getAttribute("h_id")).append("\n");
            out.append("Foglalás ID: ").append(e.getAttribute("f_id")).append("\n");

            out.append("Férőhely: ")
                .append(e.getElementsByTagName("ferohely").item(0).getTextContent()).append("\n");

            out.append("Ár: ")
                .append(e.getElementsByTagName("ar").item(0).getTextContent()).append(" Ft\n");
        }

        /* ============================
           SZOBA-VENDÉG
        ============================ */
        NodeList kapcsList = doc.getElementsByTagName("szoba-vendeg");
        out.append("\n\n=== Szoba-Vendég kapcsolatok ===\n");

        for (int i = 0; i < kapcsList.getLength(); i++) {
            Element e = (Element) kapcsList.item(i);
            out.append("\nSzoba ID: ").append(e.getAttribute("s_id")).append("\n");
            out.append("Vendég ID: ").append(e.getAttribute("v_id")).append("\n");
        }

        /* ============================
           VENDÉGEK
        ============================ */
        NodeList vendegList = doc.getElementsByTagName("vendeg");
        out.append("\n\n=== Vendégek ===\n");

        for (int i = 0; i < vendegList.getLength(); i++) {
            Element e = (Element) vendegList.item(i);

            out.append("\nVendég ID: ").append(e.getAttribute("v_id")).append("\n");
            out.append("Név: ")
                .append(e.getElementsByTagName("nev").item(0).getTextContent()).append("\n");

            out.append("Email: ")
                .append(e.getElementsByTagName("email").item(0).getTextContent()).append("\n");

            out.append("Telefon: ")
                .append(e.getElementsByTagName("telefon").item(0).getTextContent()).append("\n");
        }

        /* ============================
           VENDÉG PROFILOK
        ============================ */
        NodeList profilList = doc.getElementsByTagName("vendeg_profil");
        out.append("\n\n=== Vendég profilok ===\n");

        for (int i = 0; i < profilList.getLength(); i++) {
            Element e = (Element) profilList.item(i);

            out.append("\nProfil ID: ").append(e.getAttribute("vp_id")).append("\n");
            out.append("Vendég ID: ").append(e.getAttribute("v_id")).append("\n");

            out.append("Számlázási cím: ")
                .append(e.getElementsByTagName("szamlazasi_cim").item(0).getTextContent()).append("\n");

            out.append("Ország: ")
                .append(e.getElementsByTagName("orszag").item(0).getTextContent()).append("\n");

            out.append("Regisztráció dátuma: ")
                .append(e.getElementsByTagName("reg_datum").item(0).getTextContent()).append("\n");
        }

        /* ============================
           FOGLALÁSOK
        ============================ */
        NodeList foglList = doc.getElementsByTagName("foglalas");
        out.append("\n\n=== Foglalások ===\n");

        for (int i = 0; i < foglList.getLength(); i++) {
            Element e = (Element) foglList.item(i);

            out.append("\nFoglalás ID: ").append(e.getAttribute("f_id")).append("\n");
            out.append("Profil ID: ").append(e.getAttribute("vp_id")).append("\n");

            out.append("Érkezés: ")
                .append(e.getElementsByTagName("erkezes").item(0).getTextContent()).append("\n");

            out.append("Távozás: ")
                .append(e.getElementsByTagName("tavozas").item(0).getTextContent()).append("\n");

            Element all = (Element) e.getElementsByTagName("allapot").item(0);

            out.append("Allapot:\n");
            out.append("  függőben: ")
                .append(all.getElementsByTagName("fuggoben").item(0).getTextContent()).append("\n");
            out.append("  lemondva: ")
                .append(all.getElementsByTagName("lemondva").item(0).getTextContent()).append("\n");
            out.append("  jóváhagyva: ")
                .append(all.getElementsByTagName("jovahagyva").item(0).getTextContent()).append("\n");
        }

        /* ============================
           KIÍRÁS + FÁJLBA MENTÉS
        ============================ */
        System.out.println(out.toString());

        FileWriter fw = new FileWriter("XGBT95_read_output.txt");
        fw.write(out.toString());
        fw.close();

        System.out.println("\nKész! Kiírva ide: XGBT95_read_output.txt");
    }
}