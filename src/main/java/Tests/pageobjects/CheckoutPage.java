package Tests.pageobjects;

import MrBeatCoder.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        //Constructor for Initialization the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // driver.findElement(By.cssSelector("[placeholder='Select Country']")
    @FindBy(css="[placeholder='Select Country']")
    WebElement country;

    // By.cssSelector(".ta-results")
    By searchResults = By.cssSelector(".ta-results");

    // driver.findElement(By.cssSelector(".ta-results:nth-of-type(1)")).click();
    @FindBy(css=".ta-results:nth-of-type(1)")
    WebElement selectCountry;

    // driver.findElement(By.cssSelector(".action__submit")).click();
    @FindBy(css=".action__submit")
    WebElement submit;

    public void selectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(searchResults);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder()
    {
        submit.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }


}
