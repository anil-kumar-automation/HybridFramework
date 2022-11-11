package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class VerifyAddToCartItem extends CommonActions {

    @FindBy(xpath = "//div[@class='col-xs-15 btn-container']/div")
    WebElement clickViewCart;

    @FindBy(xpath = "//div[@class='cart-heading clearfix']/h3")
    WebElement VerifyShoppingCart;

    public VerifyAddToCartItem() {
        PageFactory.initElements(driver, this);
    }

    public void clickonviewCart() {
        clickingOnWebElement(clickViewCart,2);
    }

    public WebElement ChecktheAddCartIteamRnot() {
        return VerifyShoppingCart;
    }
}
