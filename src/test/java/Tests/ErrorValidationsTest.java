package Tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import Tests.pageobjects.CartPage;
import Tests.pageobjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {

         String productName = "ZARA COAT 3";

         landingPage.loginApplication("mrbeatcoder@gmail.com","wrongPassword");
         Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("mrbeatcoder123@gmail.com","Abcd1234!");
        productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductsTitles("ZARA COAT 33");
        Assert.assertFalse(match);// False = PASS
    }





}
