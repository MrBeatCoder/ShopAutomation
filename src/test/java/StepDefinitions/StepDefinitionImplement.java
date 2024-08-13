package StepDefinitions;

import TestComponents.BaseTest;
import Tests.pageobjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImplement extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public ConfirmationPage confirmationPage;
    public CheckoutPage checkOutpage;

@Given("We land on the ECommerce Page")
    public void we_land_on_the_ECommerce_Page() throws IOException {
    landingPage = launchApplication();
}

@Given("^We login with username (.+) and password (.+)$")
    public void we_login_with_username_and_password(String username, String password) throws Throwable
{
    productCatalogue = landingPage.loginApplication(username,password);
}

@When("^We add the Product (.+) to Cart$")
    public void We_add_the_product_to_cart(String productName) throws Throwable
{
    productCatalogue.getProductList();
    productCatalogue.addProductToCart(productName);
}

@And("^We Checkout (.+) and submit the order$")
    public void We_Checkout_Product_and_submit_the_order(String productName) throws Throwable
    {
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductsTitles(productName);
        Assert.assertTrue(match);// True = PASS
        checkOutpage = cartPage.goToCheckOut();

        checkOutpage.selectCountry("New Zealand");
        confirmationPage = checkOutpage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
        public void message_is_displayed_on_ConfirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("{string} message is displayed")
        public void login_error_message_is_displayed(String stringError)
    {
        Assert.assertEquals(landingPage.getErrorMessage(),stringError);
        driver.close();
    }

}
