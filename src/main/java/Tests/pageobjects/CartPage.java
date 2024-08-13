package Tests.pageobjects;

import MrBeatCoder.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        //Constructor for Initialization the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
    @FindBy(css=".cartSection h3")
    List<WebElement> productsTitles;

    // driver.findElement(By.cssSelector(".totalRow button")).click();
    @FindBy(css=".totalRow button")
    WebElement checkOutButton;

    public boolean verifyProductsTitles(String productName)
    {
        boolean match = productsTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckOut()
    {
        checkOutButton.click();
        CheckoutPage checkOutpage = new CheckoutPage(driver);
        return checkOutpage;
    }

}
