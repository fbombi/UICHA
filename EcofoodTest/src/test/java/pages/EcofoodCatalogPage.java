package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EcofoodCatalogPage extends EcofoodBasePage {
    @FindBy(xpath = "//a[@id='category0']")
    private WebElement frutasButton;
    @FindBy(xpath = "//a[@id='category1']")
    private WebElement verdurasButton;
    @FindBy(xpath = "//span[@class='totals']")
    private WebElement totalcart;


    private int idProduct;
    private WebElement product;
    private List<WebElement> productsCart = new ArrayList<>();

    public EcofoodCatalogPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addCatalogProduct() {
        webWait(5, 2, Data.xpaths.get("loader"));
        moveToaProduct();
        clickElement(findElement(getXpath(product, idProduct, Data.xpaths.get("add"))));
        productsCart.add(product);
    }

    private void getCatalogProducts() {
        webWait(5, 1, "//div[@class=\"row\"]");
        Map<Integer, Object> result = getProducts(Data.xpaths.get("catalogProductMatrix"));
        idProduct = (int) result.get(2);
        product = (WebElement) result.get(3);
    }

    public String getProductDetails() {
        webWait(5, 2, Data.xpaths.get("loader"));
        moveToaProduct();
        clickElement(findElement(getXpath(product, idProduct, Data.xpaths.get("search"))));
        webWait(5, 1, Data.xpaths.get("productDetail"));
        return getWebElementTextByWebElement(findElement(Data.xpaths.get("productDetail")));
    }

    public int getCatalogTotal() {
        return getProductsFromShoppingCart(totalcart, productsCart);
    }

    private void moveToaProduct() {
        clickElement(frutasButton);
        getCatalogProducts();
        moveToElement(product);
    }


}
