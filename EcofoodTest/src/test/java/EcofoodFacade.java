import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;
import utilities.Data;

public class EcofoodFacade {

    private EcofoodCatalogPage catalogPage;
    private EcofoodCheckoutPage checkoutPage;
    private EcofoodLogin loginPage;
    private EcofoodMainPage homePage;
    private EcofoodOrderPage orderPage;
    private EcofoodPayMethodPage payMethodPage;
    private EcofoodRegister registerPage;


    public EcofoodFacade(WebDriver webDriver) {
        this.homePage = PageFactory.initElements(webDriver, EcofoodMainPage.class);
    }

    public String register() {
        registerPage = homePage.getRegisterPage();
        registerPage.fillForm();
        return registerPage.getAlert();
    }

    public int login() {
        loginPage = homePage.getLoginPage();
        homePage = loginPage.fillForm(Data.data.get("nameL"), Data.data.get("passL"), 1);
        return homePage.getTotalCart();

    }

    public String unsuccessfullogin() {
        loginPage = homePage.getLoginPage();
        homePage = loginPage.fillForm(Data.data.get("nameW"), Data.data.get("passW"), 0);
        return loginPage.getMessage();
    }

    public String getLoginResponse() {

        return loginPage.getWebElementTextByXpath("//div[@class=\"header-login\"]/a[2]");
    }

    public void logout() {
        homePage = homePage.getLogoutPage();
    }

    public int addProductsHomePage() {
        login();
        addProduct(1);
        return homePage.getHomepagetotal();
    }

    public int addCatalogProducts() {
        login();
        catalogPage = homePage.getCatalogPage();
        addProduct(2);
        return catalogPage.getCatalogTotal();
    }


    public int addProduct(int option) {
        for (int i = 0; i < ((int) (Math.random() * 10) + 1); i++) {
            if (option == 1) {
                homePage.addProduct();
            } else {
                catalogPage.addCatalogProduct();
            }
            System.out.println(i + 1);
        }
        return homePage.getTotalCart();
    }

    public int addAllStock() {
        login();
        int totalProduct = homePage.addAllStock();
        int totalCart = homePage.getTotalCart();
        return totalCart - totalProduct;
    }

    public int decreaseProductQuantity() {
        int begin=loginAndAddProduct();
        int after=homePage.decreaseQuantityOfaProduct();
        return begin-after;
    }

    public int emptyCart() {
        loginAndAddProduct();
        return homePage.emptyCart();
    }

    public String productDetails() {
        login();
        catalogPage = homePage.getCatalogPage();
        System.out.println(catalogPage.getProductDetails());
        return catalogPage.getProductDetails();
    }

    public void checkoutWithoutMethod() {
        loginAndAddProduct();
        checkoutPage = homePage.getCheckoutPage();
        checkoutPage.checkoutWithoutPaymentMethod();
    }

    public void checkoutWithMethod() {
        loginAndAddProduct();
        payMethodPage = homePage.getPaymentPage();
        payMethodPage.addNewPaymentMethod();
        checkoutPage = homePage.getCheckoutPage();
        checkoutPage.checkoutWithPaymentMethod();
    }

    public int loginAndAddProduct() {
        login();
       return addProduct(1);
    }


}
