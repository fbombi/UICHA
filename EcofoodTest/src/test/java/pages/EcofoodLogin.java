package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Data;

public class EcofoodLogin extends EcofoodBasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    public EcofoodLogin(WebDriver webDriver) {
        super(webDriver);
    }

    public EcofoodMainPage fillForm(String name, String pass,int option) {
        webWait(5, 2, Data.xpaths.get("loader"));
        sendKeys(this.name, name);
        sendKeys(this.password, pass);
        clickElement(this.loginButton);
        if(option==1){
            webWait(5, 1, Data.xpaths.get("miniCart"));
        }
        return PageFactory.initElements(webDriver, EcofoodMainPage.class);

    }

    public String getMessage(){
        webWait(3,1,Data.xpaths.get("unsuccessLogin"));
        String header=getWebElementTextByXpath(Data.xpaths.get("unsuccessTitle"));
        clickElement(findElement(Data.xpaths.get("tryAgain")));
        return header;
    }



}
