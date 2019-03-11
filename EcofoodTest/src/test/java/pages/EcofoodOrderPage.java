package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EcofoodOrderPage extends EcofoodBasePage {
    //@FindBy(xpath = "//tbody[@id='orders']//td")
    @FindBy(xpath = "//tbody[@id='orders']//tr[1]")
    List<WebElement> orderNumber;

    @FindBy(xpath = "//tbody[@id='orders']//tr[4]")
    List<WebElement> details;
    public EcofoodOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void orderDetail(){


    }

}
