package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class SortBy extends CommonActions {

    @FindBy(xpath = "//div[@class='sorting-sec animBounce']/div[@class='sort-drop clearfix']")
    WebElement clickSortby;

    @FindBy(xpath = "//div[@class='sorting-sec animBounce']/ul[@style='z-index: 17;']/li[@data-sorttype='plrty']")
    WebElement popularity;

    public SortBy() {
        PageFactory.initElements(driver, this);
    }

    public void ClickonSortby() {
        clickingOnWebElement(clickSortby, 5);
    }

    public void clickpopularity() {
        clickingOnWebElement(popularity, 5);
    }

}

