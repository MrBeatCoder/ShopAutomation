package Tests.pageobjects;

import MrBeatCoder.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends AbstractComponent {

WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        //Constructor for Initialization the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //WebElement userEmailLocators = driver.findElement(By.id("userEmail"));
    //WebElement userPasswordLocators = driver.findElement(By.id("userPassword"));
    //WebElement userLoginLocators = driver.findElement(By.id("login"));

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement userLogin;

    // Locator for toaster message for incorrect login and password
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public void goToLandingPage(){
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        userLogin.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }



}
