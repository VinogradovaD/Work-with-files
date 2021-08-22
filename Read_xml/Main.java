package com.company;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {

    public static void main(String[] args) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance ().newDocumentBuilder ();
            Document document = documentBuilder.parse ("./src/com/company/file.xml");
            Node root = document.getDocumentElement ();

            System.out.println ("Russian writers:");
            System.out.println ();
            NodeList nodelist = root.getChildNodes ();
            for (int i = 0; i < nodelist.getLength (); i++) {
                Node node = nodelist.item (i);
                if (node.getNodeType () == Node.ELEMENT_NODE) {
                  NodeList nodelist1=node.getChildNodes ();
                  for (int j=0; j<nodelist1.getLength (); j++){
                      Node node1=nodelist1.item (j);
                      if (node1.getNodeType ()==Node.ELEMENT_NODE){
                          NodeList nodelist2=node1.getChildNodes ();
                              for (int g=0; g<nodelist2.getLength (); g++){
                                  Node node2=nodelist2.item (g);
                                  if (node2.getNodeType ()==Node.ELEMENT_NODE){
                                      System.out.println (node2.getNodeName ()+ ": "+ node2.getTextContent ());
                                  }
                              }
                          }

                      }
                  }
                }



            } catch(ParserConfigurationException ex){
                ex.printStackTrace (System.out);
            } catch(SAXException ex){
                ex.printStackTrace (System.out);
            } catch(IOException ex){
                ex.printStackTrace (System.out);
            }
        }
    }
