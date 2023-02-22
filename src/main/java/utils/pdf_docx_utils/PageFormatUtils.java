package utils.pdf_docx_utils;

import BrowserFactory.DriverFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class PageFormatUtils {

    public static String readPDF(String fileName) throws  IOException {
        //Load the pdf file
        PDDocument document = PDDocument.load(new File(fileName));

        //Extracting line by line
        PDFTextStripper stripper = new PDFTextStripper();

        //Converting stream to String
        String text = stripper.getText(document);

        //Closing the document
        document.close();

        System.out.println(text);

        //returning in string format
        return text;
    }
    public static void createDocx(String pdf1, String pdf2, String docx) throws IOException {
        // Create a new Word document
        XWPFDocument document = new XWPFDocument();

        // Create a new paragraph
        XWPFParagraph paragraph = document.createParagraph();

        // Add the contents of the first PDF file to the paragraph
        String pdf1Text = readPDF(pdf1);
        paragraph.createRun().setText(pdf1Text);
        System.out.println(pdf1Text);

        // Add a new paragraph
        paragraph = document.createParagraph();

        // Add the contents of the second PDF file to the paragraph
        String pdf2Text = readPDF(pdf2);
        paragraph.createRun().setText(pdf2Text);
        System.out.println(pdf2Text);

        // Write the contents of the Word document to a file
        FileOutputStream out = new FileOutputStream(new File(docx));
        document.write(out);
        out.close();
        document.close();
    }



    public static void printAndSave(String filePath) throws InterruptedException, AWTException {
        Thread.sleep(15000);
        int durationInSeconds = 40;
        Duration timeout = Duration.ofSeconds(durationInSeconds);

        // Wait for the page to fully load
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), timeout);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));

        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
            js.executeScript("print();");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Wait for the print dialog to appear
        Thread.sleep(5000);

        // Simulate pressing the "Print" button
        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);

        // Simulate typing the file name and location
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        // Simulate pressing the "Save" button in the save dialog
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
    }

}
