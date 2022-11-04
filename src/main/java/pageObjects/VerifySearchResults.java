package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class VerifySearchResults extends CommonActions {


    @FindBy(xpath = "//div[@class='search-result-txt-section  marT12']/span[@style='color: #212121; font-weight: normal']")
    WebElement SearchResult;

    public VerifySearchResults()
    {
        PageFactory.initElements(driver, this);
    }

    public WebElement VerifysearchCriteria() {
        return SearchResult;
    }
}
