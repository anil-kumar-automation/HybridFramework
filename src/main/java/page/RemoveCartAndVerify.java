package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class RemoveCartAndVerify  extends CommonActions {

    public RemoveCartAndVerify()
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='remove-item-div']" )
    WebElement removeCart;


    @FindBy(xpath = "//div[@class='empty-cart-wrapper']/div[1]/h3" )
    WebElement VerifyRemoveCart;

    public void clickRemoveCart()
    {
        clickingOnWebElement(removeCart,2);
    }

    public WebElement verifyremoveCart()
    {
        return VerifyRemoveCart;
    }

}
