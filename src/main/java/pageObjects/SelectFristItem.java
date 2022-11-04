package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

import java.util.List;

public class SelectFristItem extends CommonActions {


    @FindBy(xpath = "//p[@class='product-title']")
    List<WebElement> Item;

    public SelectFristItem() {
        PageFactory.initElements(driver, this);
    }

    public void clickOnFristIteam()
    {
        List<WebElement> ClickFristItem = Item ;
        for(int i=1;i<=1;i++)
        {
            ClickFristItem.get(i).click();
        }
    }
}


