/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author prashant
 */
public class MapPOC {
    
    	
//Using API with restAssurd is an easy way:
    public static String[] get(String address,String county) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
    address = address.replace(",", "+");
    address = address.replace(" ", "+");
    county = county.replace(" ", "");
    String thisLine;
    String fullAddress = address+"+"+county;
    System.out.println(fullAddress);

    URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+fullAddress+"&key=AIzaSyCG3OVrz2u0FJO2nZpLTIRaL1QonyLJatk");

    BufferedReader theHTML = new BufferedReader(new InputStreamReader(url.openStream()));

    FileWriter fstream = new FileWriter("url.xml");
    BufferedWriter out = new BufferedWriter(fstream);
    while ((thisLine = theHTML.readLine()) != null)
        out.write(thisLine);
    out.close();

    File file = new File("url.xml");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.parse(file);
    doc.getDocumentElement().normalize();
    NodeList nl = doc.getElementsByTagName("status");
    Element n = (Element)nl.item(0);
    String st = n.getFirstChild().getNodeValue();
if (st.equals("OK"))
    {
        System.out.println("inside if");
        NodeList n2 = doc.getElementsByTagName("lat");
        Element nn = (Element)n2.item(0);
        String st1 = nn.getFirstChild().getNodeValue();
        NodeList n3 = doc.getElementsByTagName("lng");
        Element nn1 = (Element)n3.item(0);
        String st2 = nn1.getFirstChild().getNodeValue();
         System.out.println(st1+","+st2);
        String[] str=new String[]{st1,st2};
        
        return str;
    }
    return null;
    }

    public static void main(String[] args) {
        try {
            try {
                
                String[] str=MapPOC.get("235 Park Dr,Boston", "MA");
                
                OTPSender.send("Rquest Assigned from 360 Huntington Ave,Boston"+" http://maps.google.com/?q="+str[0]+","+str[1], "+16178402624");
            } catch (MalformedURLException ex) {
                Logger.getLogger(MapPOC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(MapPOC.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(MapPOC.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(MapPOC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
