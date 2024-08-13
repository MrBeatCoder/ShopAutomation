package MrBeatCoder.AbstractComponents;

import Tests.pageobjects.CartPage;
import Tests.pageobjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    //AbstractComponent is Parent to LandingPage and ProductCatalogue Class Files
    //Pass driver from child classes to parent
    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
    @FindBy(css="[routerlink*='cart']")
    WebElement cartHeader;

    // CSS Locator for ORDERS button - [routerlink*='myorders']
    @FindBy(css="[routerlink*='myorders']")
    WebElement ordersHeader;

    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        Thread.sleep(1000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage(){
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage(){
        ordersHeader.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;
    }


}
