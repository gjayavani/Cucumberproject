package automationcucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.omg.CORBA.INITIALIZE;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StepDefinitions
{
     String baseurl = "http://automationpractice.com/index.php";
    public WebDriver driver;
    @Before
    public void openBrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseurl);
        driver.manage().window().maximize();
    }
    @After
    public void closeBrowser()
    {
     /*   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.close();*/
    }
//******************LOGIN FUNCTIONALITY********************************//
    @Given("^I am on the home page$")
    public void isUserOnHomePage()
    {
      Assert.assertTrue(driver.findElement(By.cssSelector("#home-page-tabs")).isDisplayed());
    }
    @When("^I select sign in tab$")
    public void selectsigninTab() throws InterruptedException
    {
        Thread.sleep(5000);
       driver.findElement(By.cssSelector(".login")).click();
       Thread.sleep(10000);
    }

    @Then("^I should be on the Authentication page$")
    public void isUserOnAuthenticationpage()
    {
       Assert.assertTrue(driver.findElement(By.cssSelector("#authentication")).isDisplayed());
    }

    @When("^I enter email as (.*) & password as (.*)")
    public void enterValidCredentials(String username,String password) throws InterruptedException
    {
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#email")).sendKeys(username);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
    }
    @And("^I select sign in link$")
    public void selectLoginTab() throws InterruptedException
    {
        Thread.sleep(10000);
      driver.findElement(By.cssSelector("#SubmitLogin")).click();
    }

    @Then("^I should be logged in successfully$")
    public void isUserLoggedIn() throws InterruptedException
    {
        Thread.sleep(10000);
      Assert.assertTrue(driver.getTitle().contains("My account"));
    }

    @And("^I should be on My account page$")
    public void isUserOnMyAccountPage() throws InterruptedException
    {
        Thread.sleep(10000);
        Assert.assertTrue(driver.findElement(By.cssSelector("#my-account")).isDisplayed());
    }
   @And("^I should see the message as (.*)$")
    public void verifyValidationMessage(String message)
    {
         String expectedMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
         Assert.assertEquals(expectedMessage, message);
    }
//***********************REGISTERING NEW ACCOUNT**************************//
   @When("^I enter an email as (.*)$")
    public void enterEmailToCreateAnAccount(String email)
   {
        driver.findElement(By.cssSelector("#email_create")).sendKeys(email);
   }
   @And("^click on submit tab$")
    public void userSelectCreateAccountTab() throws InterruptedException
   {
       Thread.sleep(10000);
       driver.findElement(By.cssSelector("#SubmitCreate")).click();
   }
   @Then("^I should navigate to Create an account page$")
    public void isUserOnCreateAnAccountPage() throws InterruptedException
   {
       Assert.assertTrue(driver.findElement(By.cssSelector(".page-heading")).isDisplayed());
       Thread.sleep(5000);
   }
    @And("^I should enter all the user details like title (.*),firstname (.*),lastname (.*),password (.*)$")
    public void enterUserDetails(String title,String firstname,String lastname,String password) throws InterruptedException
    {
        if(title.equalsIgnoreCase("Mr"))
        {
            driver.findElement(By.cssSelector("#id_gender1")).click();
        }
        else if(title.equalsIgnoreCase("Mrs"))
        {
            driver.findElement(By.cssSelector("#id_gender2")).click();
        }
        Thread.sleep(10000);
        driver.findElement(By.cssSelector("#customer_firstname")).sendKeys(firstname);
        driver.findElement(By.cssSelector("#customer_lastname")).sendKeys(lastname);
        driver.findElement(By.cssSelector("#passwd")).sendKeys(password);
    }
    @And("^I should enter Day (.*),Month (.*),Year (.*)$")
    public void enterDOB(String day,String month,String year)throws InterruptedException
    {
        Thread.sleep(10000);
        Select selectday = new Select(driver.findElement(By.cssSelector("#days")));
        selectday.selectByValue(day);
        Select selectmonth = new Select(driver.findElement(By.cssSelector("#months")));
        selectmonth.selectByValue(month);
        Select selectyear = new Select(driver.findElement(By.cssSelector("#years")));
        selectyear.selectByValue(year);
    }
    @And("^I should enter address (.*),city (.*),state (.*),zip (.*)$")
    public void enterCityAddressStateZip(String address,String city,String state,String zip)
    {
        driver.findElement(By.cssSelector("#address1")).sendKeys(address);
        driver.findElement(By.cssSelector("#city")).sendKeys(city);
        Select selectstate = new Select(driver.findElement(By.cssSelector("#id_state")));
        selectstate.selectByVisibleText(state);
        driver.findElement(By.cssSelector("#postcode")).sendKeys(zip);
    }
   @And("^I provide details as address,city,state,zip$")
   public void provideRegistrationDetails(DataTable table) throws InterruptedException
   {
       List<String> data = table.asList();
       String address1 = data.get(0);
       Thread.sleep(5000);
        driver.findElement(By.cssSelector("#address1")).sendKeys(address1);
        driver.findElement(By.cssSelector("#city")).sendKeys(data.get(1));
        Select selectstate = new Select(driver.findElement(By.cssSelector("#id_state")));
        selectstate.selectByVisibleText(data.get(2));
        driver.findElement(By.cssSelector("#postcode")).sendKeys(data.get(3));
    }
    @And("^I should enter mobile (.*)$")
    public void enterCountryMobileno(String mobile)
    {
        driver.findElement(By.cssSelector("#phone_mobile")).sendKeys(mobile);
    }

    @When("^I should click on Register button$")
    public void userSelectRegisterTab()
    {
        driver.findElement(By.cssSelector("#submitAccount")).click();
    }
   @Then("^I should see an error message (.*)$")
    public void userCanSeeErrorMessage(String error) throws InterruptedException
    {
        String errormessage = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li")).getText();
        Assert.assertEquals(error,errormessage);
        Thread.sleep(20000);
    }
//***************************FORGOT PASSWORD*******************************//
    @When("^I click on Forgot password link in login panel$")
    public void selectForgotPasswordLink() throws InterruptedException
    {
        driver.findElement(By.xpath("//a[@title = \"Recover your forgotten password\"]")).click();
        Thread.sleep(5000);
    }
    @Then("^I should be navigated to password recovery page$")
    public void userIsOnPasswordRecoveryPage()
    {
        Assert.assertTrue(driver.findElement(By.cssSelector(".page-subheading")).isDisplayed());
    }
    @When("^I enter an email (.*) to retrieve forgot password$")
    public void enterEmailToGetForgotPassword(String forgotemail)throws InterruptedException
    {
        driver.findElement(By.cssSelector("#email")).sendKeys(forgotemail);
        Thread.sleep(5000);
    }
   @And("^I click on Retrieve Password button$")
    public void selectRetrievePwdButton()
    {
         driver.findElement(By.xpath("//button[@class = 'btn btn-default button button-medium']")).click();

    }
   @Then("^I should see the confirmation message (.*)$")
    public void userCanSeeTheConfirmationMessage(String message)
    {
       String displayedtext = driver.findElement(By.xpath("//p[@class='alert alert-success']")).getText();
       Assert.assertTrue(displayedtext.contains(message));
    }
//**********************************SEARCHING A PRODUCT AND ADDING TO CART****************************//
    @When("^I enter the product name as (.*) in search box$")
    public void searchAProduct(String product) throws InterruptedException
    {
        Thread.sleep(10000);
     driver.findElement(By.cssSelector("#search_query_top")).sendKeys(product);
    }
    @And("^I click on search button$")
    public void selectSubmitButton() throws InterruptedException
    {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();
    }
    @When("^I select the product (.*)$")
    public void selectTheProduct(String color) throws InterruptedException
    {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//img[@title='Printed Dress']")).click();
        Thread.sleep(5000);
        switch (color)
        {
            case "pink" :
                  driver.findElement(By.cssSelector("#color_24")).click();
                  break;
            case "beige" :
                  driver.findElement(By.cssSelector("#color_7")).click();
                  break;
        }
        Thread.sleep(5000);
    }
    @And("^I add the product to the cart$")
    public void addTheCart() throws InterruptedException
    {
        driver.findElement(By.xpath("//button[@name='Submit']")).click();
        Thread.sleep(5000);
    }
    @Then("^I should see the product added to the cart$")
    public void CheckTheCart() throws InterruptedException
    {
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/h2/span[2]")).isDisplayed());
        Thread.sleep(5000);
    }
    @When("^I select the Proceed to checkout tab$")
    public void proceedToCheckoutSearch() throws InterruptedException
    {
        driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
        Thread.sleep(10000);
    }
//***************************SHOPPING CART SUMMARY*************************//
    @When("^I select the Proceed to checkout button$")
    public void proceedToCheckoutCart() throws InterruptedException
    {
       driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
       Thread.sleep(10000);
    }
    @Then("^I should be on the SHOPPING cart page$")
    public void shoppingCart() throws InterruptedException
     {
         Thread.sleep(10000);
         Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).isDisplayed());
     }
    @When("^I select the Proceed to checkout button on shopping page$")
    public void selectProceedToCheckoutCart() throws InterruptedException
    {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[2]/a[1]")).click();

    }
    @And("^I should be navigated to Addresses page to verify the billing & shipping addresses$")
    public void verifyAddresses()
    {
       Assert.assertTrue(driver.findElement(By.cssSelector(".page-heading")).isDisplayed());
    }
    @When("^I select the Proceed to checkout$")
    public void proceedCheckoutAddress() throws InterruptedException
    {
        driver.findElement(By.name("processAddress")).click();
        Thread.sleep(10000);
    }

}