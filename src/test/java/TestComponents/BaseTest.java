package TestComponents;

import Tests.pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {


    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        // Multiple Browsers in Framework - Chrome, Firefox, Edge etc.
        // Maintain one Global Properties file - set to chrome, then it will execute chrome etc.
        // Properties Class can read global properties

        Properties prod = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
        prod.load(fis);

        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prod.getProperty("browser");
                //String browserName = prod.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }

        else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

        @BeforeMethod(alwaysRun = true)
        public LandingPage launchApplication() throws IOException {
            driver = initializeDriver();
            landingPage = new LandingPage(driver);
            landingPage.goToLandingPage();
            return landingPage;
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown()
        {
            driver.close();
        }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // 1. Read Json to String
        String jsonContent = FileUtils.readFileToString(new File(filePath),
                StandardCharsets.UTF_8);

        // 2. String to HashMap with Jackson Databind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
    {
        TakesScreenshot photoTime = (TakesScreenshot)driver;
        File source = photoTime.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    }



}
