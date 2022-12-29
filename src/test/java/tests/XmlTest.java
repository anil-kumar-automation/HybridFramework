package tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import utils.XmlReader;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlTest {
    Logger log = Logger.getLogger(XmlTest.class);

    @Test
    public void XmlValuesValidate() throws IOException, ParserConfigurationException, SAXException {
        XmlReader.XmlReaderData();
        log.info("successfully printed xml value from student.xml file");
    }
}
