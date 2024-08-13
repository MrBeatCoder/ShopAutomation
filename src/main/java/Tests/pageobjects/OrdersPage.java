package Tests.pageobjects;

import MrBeatCoder.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponent {

WebDriver driver;

    public OrdersPage(WebDriver driver){
        super(driver);
        //Constructor for Initialization the driver
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    // List<WebElement> orderProducts = driver.findElements(By.cssSelector("tr td:nth-child(3)"));
    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderProductsName;

    public boolean verifyOrdersDisplay(String productName)
    {
        boolean match = orderProductsName.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }


}
