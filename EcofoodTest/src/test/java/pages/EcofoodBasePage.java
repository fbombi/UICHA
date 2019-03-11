package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcofoodBasePage {
    protected WebDriver webDriver;
    protected Actions action;

    public EcofoodBasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        action = new Actions(webDriver);
    }

    protected void sendKeys(WebElement field, String data) {
        field.sendKeys(data);
    }

    protected void clickElement(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webElement);
    }

    protected void webWait(int time, int option, String xpath) {
        switch (option) {
            case 1:
                new WebDriverWait(webDriver, time).until(ExpectedConditions.
                        visibilityOfElementLocated(By.xpath(xpath)));
                break;
            case 2:
                new WebDriverWait(webDriver, time).until(ExpectedConditions.
                        invisibilityOfElementLocated(By.xpath(xpath)));
                break;
            case 3:
                new WebDriverWait(webDriver, time).until(ExpectedConditions.alertIsPresent());
                break;
            default:

                break;
        }

    }

    public WebElement findElement(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    public List<WebElement> findElements(String xpath) {
        return (webDriver.findElements(By.xpath(xpath)));


    }

    public String getWebElementTextByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath)).getText();
    }

    public String getWebElementTextByWebElement(WebElement element) {
        return element.getText();
    }

    protected String getXpath(WebElement product, int idProduct, String action) {
        String[] path = product.toString().split("xpath: ");
        return path[1].substring(0, path[1].length() - 1) + "[" + idProduct + "]" + action;
    }

    protected void moveToElement(WebElement element) {
        action.moveToElement(element).click().perform();
    }

    protected void selectElement(String xpath, String option) {
        new Select(findElement(xpath)).selectByVisibleText(option);
    }

    protected void putData(List<WebElement> elements, String[] data) {
        for (int i = 0; i < elements.size(); i++) {
            sendKeys(elements.get(i), data[i]);
        }
    }

    protected String[] getData(String id) {
        String data[] = new String[7];
        for (int i = 0; i < 7; i++) {
            data[i] = Data.data.get(id + Integer.toString(i + 1));
        }
        return data;
    }

    protected Map<Integer, Object> getProducts(String path) {
        Map<Integer, Object> result = new HashMap<>();
        List<WebElement> products = (webDriver.findElements(By.xpath(path)));
        int idProduct = (int) (Math.random() * (products.size() - 1) + 1);
        WebElement product = products.get(idProduct);
        result.put(1, products);
        result.put(2, idProduct);
        result.put(3, product);
        return result;
    }

    protected int getProductsFromShoppingCart(WebElement totalcart,List productsCart) {
        return Integer.parseInt(getWebElementTextByWebElement(totalcart))-productsCart.size();
    }
}
