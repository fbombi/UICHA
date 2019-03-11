package pages;//button[@id='sub_new']

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Data;

import java.util.ArrayList;
import java.util.List;

public class EcofoodPayMethodPage extends EcofoodBasePage {
    @FindBy(xpath = "//button[@id='list_payment_btn']")
    WebElement addButton;

    private List<WebElement> paymentFields = new ArrayList<WebElement>();

    public EcofoodPayMethodPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addNewPaymentMethod() {
        clickElement(addButton);
        webWait(5, 1, Data.xpaths.get("methodForm"));
        paymentFields = findElements(Data.xpaths.get("inputMethodForm"));
        putData(paymentFields, getData("p"));
        clickElement(findElement(Data.xpaths.get("addMethod")));
        webWait(3, 1, Data.xpaths.get("emergentWindow"));
        clickElement(findElement(Data.xpaths.get("thanksButton")));
    }


}
