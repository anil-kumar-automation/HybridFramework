package utils.selenium;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XmlReader {

    static Logger log = Logger.getLogger(XmlReader.class);


    public static void getAllStudents(Document doc)
    {
        NodeList studentNodes = doc.getElementsByTagName("student");
        for(int i=0; i<studentNodes.getLength(); i++)
        {
            Node studentNode = studentNodes.item(i);
            if(studentNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element studentElement = (Element) studentNode;
                String studentId = studentElement.getElementsByTagName("id").item(0).getTextContent();
                String studentName = studentElement.getElementsByTagName("name").item(0).getTextContent();
                log.info("Student Id = " + studentId);
                log.info("Student Name = " + studentName);
            }
        }
    }

    public static void getStudentById(Document doc, String textNodeName, String textNodeValue)
    {
        NodeList studentNodes = doc.getElementsByTagName("company");
        for(int i=0; i<studentNodes.getLength(); i++)
        {
            Node studentNode = studentNodes.item(i);
            if(studentNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element studentElement = (Element) studentNode;
                NodeList textNodes = studentElement.getElementsByTagName(textNodeName);
                if(textNodes.getLength() > 0)
                System.out.println(textNodes.getLength());
                {
                    if(textNodes.item(0).getTextContent().equalsIgnoreCase(textNodeValue))
                    {
                        log.info(textNodes.item(0).getTextContent());
                        log.info(textNodes.item(1).getTextContent());
                        log.info(studentElement.getElementsByTagName("name").item(0).getTextContent());
                        log.info(studentElement.getElementsByTagName("name").item(1).getTextContent());
                        log.info(studentElement.getElementsByTagName("name").item(2).getTextContent());
                    }
                }
            }
        }
    }

    public static void  XmlReaderData() throws IOException, ParserConfigurationException, SAXException {
        File xmlFile = new File(System.getProperty("user.dir") + "/src/test/resources/student.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement();
        getAllStudents(doc);
        getStudentById(doc,"id", "3");
    }
}
