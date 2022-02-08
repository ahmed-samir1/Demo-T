package API_Task;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class XML1 {


    @Test()
    public void Verify_Price_XML() throws ParserConfigurationException, IOException, SAXException {
        File file = new File(".\\src\\test\\java\\API_Task\\SOAP.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        //System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        String price = doc.getElementsByTagName("m:GetStockPriceResponse").item(0).getTextContent();
        //System.out.println("Price data: " + doc.getElementsByTagName("m:GetStockPriceResponse").item(0).getTextContent());
        Assert.assertEquals(price, "34.5", "Correct price received in the Response");

    }
}


