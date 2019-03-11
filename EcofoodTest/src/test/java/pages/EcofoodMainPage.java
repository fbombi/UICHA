package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EcofoodMainPage extends EcofoodBasePage {

    @FindBy(xpath = "//a[@href=\"/login/\"]")
    private WebElement login;

    @FindBy(xpath = "//a[@href=\"/regUser/\"]")
    private WebElement registrarse;

    @FindBy(xpath = "//a[@href=\"/logout/\"]")
    private WebElement logout;

    @FindBy(xpath = "//a[@href=\"/catalogo/\"]")
    private WebElement catalog;

    @FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
    private WebElement shoppingcart;

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement totalcart;

    @FindBy(xpath = "//button[@class='au-btn au-btn-primary au-btn-radius btn-checkout']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//a[@href='/paymentMethods/']")
    private WebElement paymentMethod;

    private List<WebElement> productsCart = new ArrayList<WebElement>();
    private List<String> nameProducts = new ArrayList<String>();
    private List<Integer> idProducts = new ArrayList<Integer>();
    private int idProduct;
    private WebElement product;

    public EcofoodMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EcofoodLogin getLoginPage() {
        webWait(5, 2, Data.xpaths.get("loader"));
        clickElement(this.login);
        return PageFactory.initElements(webDriver, EcofoodLogin.class);
    }

    public EcofoodRegister getRegisterPage() {
        clickElement(this.registrarse);
        return PageFactory.initElements(webDriver, EcofoodRegister.class);
    }

    public EcofoodMainPage getLogoutPage() {
        clickElement(this.logout);
        return PageFactory.initElements(webDriver, EcofoodMainPage.class);
    }

    private void getWeekProduct() {
        webWait(5, 2, Data.xpaths.get("loader"));
        webWait(10, 1, Data.xpaths.get("productMatrix"));
        Map<Integer, Object> result = getProducts(Data.xpaths.get("productMatrix"));
        idProduct = (int) result.get(2);
        product = (WebElement) result.get(3);
    }

    public void addProduct() {
        webWait(5, 2, Data.xpaths.get("loader"));
        getWeekProduct();
        putProduct();
    }

    private void putProduct() {
        moveToElement(product);
        clickElement(findElement(getXpath(product, idProduct, Data.xpaths.get("add"))));
        setIdProductsAdded(idProduct);
        String[] a = product.getText().split("\n");
        setNameProductsAdded(a[0]);
        productsCart.add(product);
    }

    public EcofoodCatalogPage getCatalogPage() {
        clickElement(this.catalog);
        return PageFactory.initElements(webDriver, EcofoodCatalogPage.class);
    }

    public int getHomepagetotal() {
        return getProductsFromShoppingCart(totalcart, productsCart);
    }

    public int addAllStock() {
        getWeekProduct();
        moveToElement(findElement(getXpath(product, idProduct, Data.xpaths.get("total"))));
        webWait(2, 1, getXpath(product, idProduct, Data.xpaths.get("total")));
        String total = findElement(getXpath(product, idProduct, Data.xpaths.get("total"))).getText();
        for (int i = 0; i < Integer.parseInt(total); i++) {
            putProduct();
            System.out.println(i+1);
        }
        return Integer.parseInt(total);
    }

    public int getTotalCart(){
        return Integer.parseInt(getWebElementTextByXpath(Data.xpaths.get("numberCart")));
    }
    public int decreaseQuantityOfaProduct() {
        moveToElement(product);
        clickElement(findElement(getXpath(product, idProduct, Data.xpaths.get("minus"))));
        return getTotalCart();
    }

    private void setIdProductsAdded(int idProduct) {
        if (!idProducts.contains(idProduct)) {
            idProducts.add(idProduct);
        }
    }

    private void setNameProductsAdded(String name) {
        if (!nameProducts.contains(name)) {
            nameProducts.add(name);
        }
    }

    public int emptyCart() {
        for (Integer idProduct1 : idProducts) {
            moveToElement(product);
            clickElement(findElement(getXpath(product, idProduct1, Data.xpaths.get("remove"))));
        }
        return getTotalCart();
    }

    public EcofoodCheckoutPage getCheckoutPage() {
        webWait(5, 1, "//i[@class='fa fa-shopping-cart']");
        clickElement(shoppingcart);
        webWait(5, 1, Data.xpaths.get("cart"));
        clickElement(checkoutButton);
        webWait(5, 2, Data.xpaths.get("loader"));
        return PageFactory.initElements(webDriver, EcofoodCheckoutPage.class);
    }

    public EcofoodPayMethodPage getPaymentPage() {
        clickElement(paymentMethod);
        return PageFactory.initElements(webDriver, EcofoodPayMethodPage.class);
    }
}



