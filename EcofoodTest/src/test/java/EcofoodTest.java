import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EcofoodTest {
    WebDriver webDriver;
    private EcofoodFacade ecofoodFacade;

    @Test
    public void successfulRegister() {
        String message = ecofoodFacade.register();
        assertThat("Usuario Creado", message, equalTo("Usuario creado"));
    }

    @Test
    public void successfulLogin() {
        ecofoodFacade.login();
        String response = ecofoodFacade.getLoginResponse();
        assertThat("The user logged in", response, equalTo("Logout"));
    }

    @Test
    public void unsuccessfulLogin() {
        String response = ecofoodFacade.unsuccessfullogin();
        assertThat("The user logged in", response, equalTo("¡HA OCURRIDO UN ERROR!"));

    }

    @Test
    public void logout() {
        ecofoodFacade.login();
        ecofoodFacade.logout();
        String response = ecofoodFacade.getLoginResponse();
        assertThat("The user logged in", response, equalTo("Registrarse"));
    }

    @Test
    public void AddProductsFromHomePage() {
        int productsAdded = ecofoodFacade.addProductsHomePage();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));

    }

    @Test
    public void AddProductsFromCatalog() {
        int productsAdded = ecofoodFacade.addCatalogProducts();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));

    }

    @Test
    public void AddProductAsUnauthenticatedUser() {
        int productsAdded = ecofoodFacade.addProduct(1);
        int after=ecofoodFacade.login();
        assertThat("SuccessfulAddProducts", productsAdded-after, equalTo(0));
    }

    @Test
    public void AddAllStockProduct() {
        int result=ecofoodFacade.addAllStock();
        assertThat("SuccessfulAddProducts", result, equalTo(0));
    }

    @Test
    public void decreaseTheQuantity() {
        int result=ecofoodFacade.decreaseProductQuantity();
        assertThat("SuccessfulAddProducts", result, equalTo(1));
    }

    @Test
    public void emptyShoppingCart() {
        int result=ecofoodFacade.emptyCart();
        assertThat("SuccessfulAddProducts", result, equalTo(0));

    }

    @Test
    public void productDetailsAsALoggedUser() {
       String result=ecofoodFacade.productDetails();
        Assert.assertNotNull(result);
    }

    @Test
    public void successfulCheckoutWithUnregisteredPayment() {
        ecofoodFacade.checkoutWithoutMethod();
    }

    @Test
    public void successfulCheckoutWithRegisteredPayment() {
        ecofoodFacade.checkoutWithMethod();
    }

    @Test
    public void verifyOrder() {
        ecofoodFacade.checkoutWithMethod();
    }


    @Before
    public void setup() {
        Data.dataRead("C:/Users/FABIO ANDRES/Desktop/EcofoodTest/data.txt", 1);
        Data.dataRead("C:/Users/FABIO ANDRES/Desktop/EcofoodTest/xpaths.txt", 0);
        webDriver = new ChromeDriver();
        webDriver.get(Data.data.get("URL"));
        ecofoodFacade = new EcofoodFacade(webDriver);
    }

    @After
    public void after() {
        //webDriver.quit();
    }
}
