package utils.pdf_docx_utils;

import java.io.File;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;


public class CombinedPdf {

    public static void mergedPdf(String Pdf1,String Pdf2,String targetPdf){
        try {
            // Create a document object
            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(targetPdf));
            document.open();

            // Read in the first PDF file
            PdfReader reader1 = new PdfReader(Pdf1);
            copy.addDocument(reader1);

            // Read in the second PDF file
            PdfReader reader2 = new PdfReader(Pdf2);
            copy.addDocument(reader2);

            // Close the document and readers
            document.close();
            reader1.close();
            reader2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
