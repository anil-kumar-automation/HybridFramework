package page;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveFristItemNdPriceInExcel extends CommonActions {

    @FindBy(xpath = "//p[@class='product-title']")
    List<WebElement> Saveitemname;
    @FindBy(xpath = "//span[@class='lfloat product-price']")
    List<WebElement> Saveitemprice;

    public SaveFristItemNdPriceInExcel() {
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getitemname() {
        return Saveitemname;
    }

    public List<WebElement> getitemprice() {
        return Saveitemprice;
    }

    public void ExcelWriter() throws IOException {

        List<WebElement> Title_List = getitemname();
        List<WebElement> Price_List = getitemprice();


        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        for (int row = 0; row < 5; row++) {
            sheet.createRow(row);
            String productname = Title_List.get(row).getText();
            String pricevalue = Price_List.get(row).getText();
            sheet.getRow(row).createCell(0).setCellValue(productname);
            sheet.getRow(row).createCell(1).setCellValue(pricevalue);
        }

        File file = new File(System.getProperty("user.dir") + "/ExcelFileData/WriteItemData.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        workbook.close();
        System.out.println("data write succesfully");
    }
}

