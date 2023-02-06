package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Waiter;

public class CarvanaTest extends CarvanaBaseTest{

    @Test(priority = 1)
    public void validateTitleAndUrl(){
        Assert.assertEquals(driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/");
    }

    @Test(priority = 2)
    public void validateLogo(){
        Assert.assertTrue(carvanaBasePage.logo.isDisplayed());
    }

    @Test(priority = 3)
    public void validateNavigationItems(){
        Assert.assertTrue(carvanaBasePage.navigationItems.isDisplayed());
    }

    @Test(priority = 4, description = "Validate the sign in error message")
    public void validateSignInErrorMessage(){
        carvanaBasePage.signInButton.click();
        Waiter.pause(3);
        carvanaBasePage.emailInputBox.sendKeys("johndoe@gmail.com");
        carvanaBasePage.continueButton.click();
        carvanaBasePage.passwordInputBox.sendKeys("abcd1234");
        carvanaBasePage.submitButton.click();
        Assert.assertEquals(carvanaBasePage.errorMessage.getText(), "Email address and/or password combination is incorrect.");
    }

    @Test(priority = 5)
    public void validateSearchOptions(){
        Waiter.pause(2);
        carvanaBasePage.searchCarsButton.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.carvana.com/cars");
        Assert.assertEquals(carvanaBasePage.filterOptions.getText(), "PAYMENT & PRICE\n" +
                "MAKE & MODEL\n" +
                "BODY TYPE\n" +
                "YEAR & MILEAGE\n" +
                "FEATURES\n" +
                "MORE FILTERS");
    }

    @Test(priority = 6)
    public void validateSearchResultTiles(){
        Waiter.pause(2);
        carvanaBasePage.searchCarsButton.click();
        carvanaBasePage.searchBox.sendKeys("mercedes-benz");
        carvanaBasePage.goButton.click();
        Waiter.pause(2);
        Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz"));

        for (int i = 0; i < carvanaBasePage.resultTiles.size(); i++) {
            Assert.assertTrue(carvanaBasePage.vehicleImage.isDisplayed());
            Assert.assertTrue(carvanaBasePage.favoriteButton.isEnabled());
            Assert.assertTrue(carvanaBasePage.tileBody.get(i).isDisplayed());

            for (int j = 0; j < carvanaBasePage.tileBody.size(); j++) {
                Assert.assertTrue(carvanaBasePage.inventoryType.isDisplayed());
                Assert.assertTrue(carvanaBasePage.ymmInformation.isDisplayed());
                Assert.assertTrue(carvanaBasePage.trimMileageInfo.isDisplayed());
                Assert.assertTrue(carvanaBasePage.price.isDisplayed());
                Assert.assertTrue(carvanaBasePage.monthlyPayment.isDisplayed());
                Assert.assertTrue(carvanaBasePage.downPayment.isDisplayed());
                Assert.assertTrue(carvanaBasePage.deliveryChip.isDisplayed());
            }
        }
    }
}