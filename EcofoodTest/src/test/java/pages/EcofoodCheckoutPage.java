package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Data;

import java.util.ArrayList;
import java.util.List;

public class EcofoodCheckoutPage extends EcofoodBasePage {
    @FindBy(xpath = "//*[@id='purchase']")
    WebElement purchase;

    private List<WebElement> formFields = new ArrayList<WebElement>();
    private List<WebElement> paymentFields = new ArrayList<WebElement>();
    private List<WebElement> paymentList = new ArrayList<WebElement>();

    public EcofoodCheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkoutWithoutPaymentMethod() {
        fillForm();
        paymentForm();
        clickElement(purchase);
        webWait(3, 1, Data.xpaths.get("emergentWindow"));
        clickElement(findElement(Data.xpaths.get("thanksButton")));

    }

    public void checkoutWithPaymentMethod() {
        fillForm();
        paymentList = findElements(Data.xpaths.get("methodList"));
        clickElement(paymentList.get((int) (Math.random() * (paymentList.size() - 1))));
        clickElement(purchase);
        webWait(3, 1, Data.xpaths.get("emergentWindow"));
        clickElement(findElement(Data.xpaths.get("thanksButton")));

    }

    private void fillForm() {
        webWait(5, 2, Data.xpaths.get("loader"));
        webWait(5, 1, Data.xpaths.get("checkoutForm"));
        formFields = findElements(Data.xpaths.get("checkoutForm"));
        putData(formFields, getData("c"));
        selectElement(Data.xpaths.get("selectCountry"), Data.data.get("country"));
        selectElement(Data.xpaths.get("selectDpto"), "Bogotá D.C");
    }

    private void paymentForm() {
        paymentFields = findElements(Data.xpaths.get("paymentForm"));
        putData(paymentFields, getData("p"));
    }


}
