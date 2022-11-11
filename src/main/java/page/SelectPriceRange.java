package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class SelectPriceRange extends CommonActions {


    @FindBy(xpath = "//input[@name='fromVal']")
    WebElement clickFristPrice;

    @FindBy(xpath = "//input[@name='toVal']")
    WebElement clickLastPrice;

    @FindBy(xpath = "//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")
    WebElement clickGoButton;

    public SelectPriceRange() {
        PageFactory.initElements(driver, this);
    }

    public void clickFristPriceRange() {
        sendKeysAndClearClick(clickFristPrice,"700");
    }

    public void clickLastPriceRange() {
        sendKeysAndClearClick(clickLastPrice,"3000");
    }

    public void clickonGOButton() {
        clickingOnWebElement(clickGoButton,2);
    }

}

