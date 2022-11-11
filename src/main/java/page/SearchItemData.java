package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonActions;

public class SearchItemData extends CommonActions {

    @FindBy(css = "input#inputValEnter.col-xs-20.searchformInput.keyword")
    WebElement itemSearch;

    @FindBy(css = "span.searchTextSpan")
    WebElement SearchButton;

    public SearchItemData() {
        PageFactory.initElements(driver, this);
    }

    public WebElement enterItemInSearch() {
        return itemSearch;
    }

    public void ClickSearchButton() {
        clickingOnWebElement(SearchButton, 5);
    }
}


