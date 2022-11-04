package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class AddToCart extends CommonActions {

    @FindBy(xpath = "//div[@id='add-cart-button-id']")
    //@FindBy(xpath = "//div[@id='add-cart-button-id234']") //wrong xpath
    WebElement clickAddtoCart;

    public AddToCart() {
        PageFactory.initElements(driver, this);
    }

    public void SelectAddtoCart() {
        clickingOnWebElement(clickAddtoCart,2);
    }
}

