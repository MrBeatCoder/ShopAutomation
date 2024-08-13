package Tests;

import TestComponents.BaseTest;
import Tests.pageobjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData", groups="Purchase")
    public void SubmitOrder(HashMap<String,String>input) throws InterruptedException, IOException {

         ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
         productCatalogue.getProductList();
         productCatalogue.addProductToCart(input.get("newproductName"));
         CartPage cartPage = productCatalogue.goToCartPage();

         boolean match = cartPage.verifyProductsTitles(input.get("newproductName"));
         Assert.assertTrue(match);// True = PASS
         CheckoutPage checkOutpage = cartPage.goToCheckOut();

         checkOutpage.selectCountry("New Zealand");
         ConfirmationPage confirmationPage = checkOutpage.submitOrder();

         String confirmMessage = confirmationPage.getConfirmationMessage();
         Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    // Verify product in Orders Page
    @Test(dependsOnMethods = {"SubmitOrder"})
    public void ordersHistory()
    {
        ProductCatalogue productCatalogue = landingPage.loginApplication("mrbeatcoder@gmail.com","Abcd1234!");
        OrdersPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrdersDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//Data//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
    }

    //@DataProvider
    //public Object[][] getData()
    //{
    //      return new Object[][] {{"mrbeatcoder@gmail.com","Abcd1234!","ZARA COAT 3"},{"mrbeatcoder123@gmail.com","Abcd1234!","ADIDAS ORIGINAL"},
    //              {"mrbeatcoder@gmail.com","Abcd1234!","IPHONE 13 PRO"}};
    //}

    //   HashMap<String,String> map = new HashMap<String,String>();
    //   map.put("email","mrbeatcoder@gmail.com");
    //   map.put("password","Abcd1234!");
    //   map.put("newproductName","ZARA COAT 3");

    //   HashMap<String,String> map1 = new HashMap<String,String>();
    //   map1.put("email","mrbeatcoder123@gmail.com");
    //   map1.put("password","Abcd1234!");
    //   map1.put("newproductName","ADIDAS ORIGINAL");

    //   HashMap<String,String> map2 = new HashMap<String,String>();
    //   map2.put("email","mrbeatcoder@gmail.com");
    //   map2.put("password","Abcd1234!");
    //   map2.put("newproductName","IPHONE 13 PRO");

}
