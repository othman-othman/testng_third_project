package scripts;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CarvanaBasePage;
import utilities.ConfigReader;
import utilities.Driver;

public class CarvanaBaseTest {

    CarvanaBaseTest carvanaBaseTest;
    CarvanaBasePage carvanaBasePage;
    CarvanaTest carvanaTest;
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("appURL"));
        carvanaBaseTest = new CarvanaBaseTest();
        carvanaBasePage = new CarvanaBasePage();
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }
}