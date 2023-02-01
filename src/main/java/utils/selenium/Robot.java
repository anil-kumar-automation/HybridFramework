package utils.selenium;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class Robot  {

    /* To Press ENTER Key using Robot */
    public void hitEnter() throws Exception {
       java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }


    /* To Press BACKSPACE Key using Robot */
    public void hitBackspace() throws Exception {
      java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_BACK_SPACE);
        re.keyRelease(KeyEvent.VK_BACK_SPACE);
    }


    /* To Press DELETE Key using Robot */
    public void hitDelete() throws Exception {
      java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_DELETE);
        re.keyRelease(KeyEvent.VK_DELETE);
    }


    /* To Select all the Text/Web Elements using ROBOT */
    public void selectAll() throws Exception {
       java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_A);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_A);
    }


    /* To Copy all the Selected Text/Web Elements using ROBOT */
    public void copyAll() throws Exception {
      java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_C);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_C);
    }


    /* To Paste all the Selected Text/Web Elements using ROBOT */
    public void pasteAll() throws Exception {
     java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyRelease(KeyEvent.VK_V);
    }


    /* To Capture Screenshot(Replaces if already exists) */
    /*
     * Also, Make sure that the automation in running in the foreground to
     * capture the Image of the Browser. Else, It'll capture the open Window
     */
    public void robotScreenCapture(String robotImageName) throws Exception {
     java.awt.Robot re = new java.awt.Robot();
        Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage bufferedImage = re.createScreenCapture(area);
        // Save as PNG
        File file = new File(robotImageName);
        if (file.exists()) {
            file.delete();
        }
        ImageIO.write(bufferedImage, "png", file);
    }


    /* To ZoomOut */
    public void robotZoomOut() throws Exception {
       java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_SUBTRACT);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ZoomIn */
    public void robotZoomIn() throws Exception {
     java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_ADD);
        re.keyRelease(KeyEvent.VK_CONTROL);
    }


    /* To ScrollUp using ROBOT */
    public void robotScrollUp() throws Exception {
       java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_PAGE_UP);
        re.keyRelease(KeyEvent.VK_PAGE_UP);
    }


    /* To ScrollDown using ROBOT */
    public void robotScrollDown() throws Exception {
      java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_PAGE_DOWN);
        re.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }

    /* To Upload a File using JAVA AWT ROBOT */
    public void fileUpload(String FileToUpload) throws Exception {
        Thread.sleep(5000);
        StringSelection filetocopy = new StringSelection(FileToUpload);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
        Thread.sleep(1000);
        java.awt.Robot re = new java.awt.Robot();
        re.keyPress(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_V);
        re.keyRelease(KeyEvent.VK_CONTROL);
        re.keyPress(KeyEvent.VK_ENTER);
        re.keyRelease(KeyEvent.VK_ENTER);
    }

    /* To Move cursor to the Desired Location */
    public void moveCursor(int X_Position, int Y_Position) throws Exception {
        java.awt.Robot re = new java.awt.Robot();
        re.mouseMove(X_Position, Y_Position);
    }
}
