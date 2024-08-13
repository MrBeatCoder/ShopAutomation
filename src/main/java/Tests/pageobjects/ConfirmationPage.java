package Tests.pageobjects;

import MrBeatCoder.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {

WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver);
        //Constructor for Initialization the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // driver.findElement(By.cssSelector(".hero-primary")
    @FindBy(css=".hero-primary")
    WebElement confirmationMessage;

    public String getConfirmationMessage()
    {


        return confirmationMessage.getText();
    }


}
