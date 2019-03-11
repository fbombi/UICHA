package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Data;

public class EcofoodRegister extends EcofoodBasePage {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='pass1']")
    private WebElement password1;

    @FindBy(xpath = "//input[@id='pass2']")
    private WebElement password2;

    @FindBy(xpath = "//button[@id='register']")
    private WebElement registrarseButton;

    private String alertText;

    public EcofoodRegister(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillForm() {
        webWait(5, 2, Data.xpaths.get("loader"));
        sendKeys(this.name, Data.data.get("nameR"));
        sendKeys(this.lastname, Data.data.get("lastnameR"));
        sendKeys(this.username, Data.data.get("usernameR"));
        sendKeys(this.email, Data.data.get("emailR"));
        sendKeys(this.password1, Data.data.get("passR"));
        sendKeys(this.password2, Data.data.get("pass2R"));
        clickElement(this.registrarseButton);
        setAlertText();
    }

    private void setAlertText() {
        webWait(5, 3, "");
        alertText = webDriver.switchTo().alert().getText();
    }

    public String getAlert() {
        return alertText;
    }


}
