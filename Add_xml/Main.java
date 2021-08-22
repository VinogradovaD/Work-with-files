package com.company;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;


public class Main {
    static ArrayList<Sportsman> arrayList = new ArrayList<> ();

    public static void main(String[] args) {
        Print ();
        NewSportsman ();
        NewFile ();
    }

    /*задание 2: чтение файла и размещение в коллекцию*/
    public static void ReadXml() {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
            Document document = documentBuilder.parse ("./src/com/company/sportsman.xml");
            Node root = document.getDocumentElement ();
            NodeList nodeList = root.getChildNodes ();
            for (int i = 0; i < nodeList.getLength (); i++) {
                String name = "", bday = "", gender = "";
                Node node = nodeList.item (i);
                if (node.hasAttributes ()) {
                    name = node.getAttributes ().getNamedItem ("name").getTextContent ();
                    bday = node.getAttributes ().getNamedItem ("birthday").getTextContent ();
                    gender = node.getAttributes ().getNamedItem ("s").getTextContent ();
                }
                Sportsman sportsman = new Sportsman (name, bday, gender);
                arrayList.add (sportsman);
                NodeList nodeList1 = node.getChildNodes ();
                for (int j = 0; j < nodeList1.getLength (); j++) {
                    Node node1 = nodeList1.item (j);
                    if (node1.getNodeType () == Node.ELEMENT_NODE && node1.hasAttributes ()) {
                        Element element = (Element) node1;
                        sportsman.addYear (Integer.parseInt (node1.getAttributes ().getNamedItem ("year").getTextContent ()));
                        sportsman.addPlace (node1.getAttributes ().getNamedItem ("place").getTextContent ());
                        sportsman.addResult (Integer.parseInt (element.getElementsByTagName ("result").item (0).getTextContent ()));
                        sportsman.addAward (element.getElementsByTagName ("award").item (0).getTextContent ());
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace (System.out);
        } catch (SAXException ex) {
            ex.printStackTrace (System.out);
        } catch (IOException ex) {
            ex.printStackTrace (System.out);
        }
    }

    /*задание 3*/
    public static void Print() {
        ReadXml ();
        /*выводим на экран имена и др всех мужчин*/
        for (Sportsman value : arrayList) {
            if (value.getGender ().equals ("м")) {
                System.out.println (value.getName () + "  " + value.getBirthday ());
            }
        }
        /*выводим на экран имена, др и медали женщин старше 1985*/
        for (Sportsman value : arrayList) {
            if (value.getGender ().equals ("ж") && Integer.parseInt (value.getBirthday ().substring (0, 4)) > 1985) {
                int j = 0;
                while (!value.getAward (j).equals ("-1")) j++;
                System.out.println (value.getName () + "  " + value.getBirthday () + "  Количество наград: " + j);
            }
        }
        /*выводим  на экран имена и результаты спортсменов, участвовавших в 2002 в соревнованиях в Москве */
        for (Sportsman sportsman : arrayList) {
            int j = 0;
            while (sportsman.getYear (j) != -1) {
                if (sportsman.getYear (j) == 2002 && sportsman.getPlace (j).equals ("москва")) {
                    System.out.println (sportsman.getName () + "  " + sportsman.getResult (j));
                }
                j++;
            }
        }
    }

    /*задание 4: вводим с консоли и добавляем в коллекцию данные еще одного спортсмена */
    public static void NewSportsman() {
        System.out.println ("Введите данные нового спортсмена: ");
        Scanner in = new Scanner (System.in);

        System.out.print ("Имя: ");
        String name = in.next ();
        System.out.print ("Дата рождения (формат гггг-мм-дд): ");
        String bday = in.next ();
        System.out.print ("Пол м или ж: ");
        String gender = in.next ();
        Sportsman sportsman = new Sportsman (name, bday, gender);
        System.out.print ("Количество соревнований: ");
        int k = in.nextInt ();
        for (int i = 0; i < k; i++) {
            System.out.print ("Город: ");
            sportsman.addPlace (in.next ());
            System.out.print ("Год: ");
            sportsman.addYear (in.nextInt ());
            System.out.print ("Результат: ");
            sportsman.addResult (in.nextInt ());
            System.out.print ("Награда: ");
            sportsman.addAward (in.next ());
        }
    }

    /*задание 5: создаем новый xml-файл с именами, количеством соревнований  и суммой очков*/
    public static void NewFile() {
        try {
            Document document = DocumentBuilderFactory.newInstance ().newDocumentBuilder ().parse ("./src/com/company/sportsman.xml");
            Document document_new = DocumentBuilderFactory.newInstance ().newDocumentBuilder ().newDocument ();
            Element rootElement = document_new.createElement ("Sportsman");
            document_new.appendChild (rootElement);
            Node root = document.getDocumentElement ();
            NodeList nodeList = root.getChildNodes ();
            for (int i = 0; i < nodeList.getLength (); i++) {
                Node node = nodeList.item (i);
                ReadXml ();
                Sportsman sportsman = arrayList.get (i);
                int sum = 0;
                int j = 0;
                while (sportsman.getResult (j) != -1) {
                    sum += sportsman.getResult (j);
                    j++;
                }
                Element tag = document_new.createElement ("Sportsman");
                Element tag2 = document_new.createElement ("events");
                Element tag3 = document_new.createElement ("results");
                tag3.appendChild (document_new.createTextNode (String.valueOf (sum)));
                tag2.setAttribute ("number", String.valueOf (j));
                tag2.appendChild (tag3);
                if (node.hasAttributes ()) {
                    tag.setAttribute ("name", node.getAttributes ().getNamedItem ("name").getTextContent ());
                    tag.appendChild (tag2);
                    rootElement.appendChild (tag2);
                }
            }
            Transformer transformer = TransformerFactory.newInstance ().newTransformer ();
            transformer.setOutputProperty (OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource (document_new);
            StreamResult file = new StreamResult (new File ("New_File.xml"));
            transformer.transform (source, file);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace (System.out);
        } catch (SAXException ex) {
            ex.printStackTrace (System.out);
        } catch (IOException ex) {
            ex.printStackTrace (System.out);
        } catch (TransformerException ex) {
            ex.printStackTrace (System.out);
        }
    }

}
