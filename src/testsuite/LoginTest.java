package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {
    String baseUrl = "https://courses.ultimateqa.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldNavigateToLoginPageSuccessfully() {
        //click on the ‘Sign In’ link
        driver.findElement(By.xpath("//a[@href='/users/sign_in']")).click();
        //Verify the text ‘Welcome Back!’
        String expectedText = "Welcome Back!";
        WebElement actualTextElement = driver.findElement(By.xpath("//h2[@class='page__heading']"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Text not found", expectedText, actualText);
    }

    @Test
    public void verifyTheErrorMessage() {
        //click on the ‘Sign In’ link
        driver.findElement(By.xpath("//a[@href='/users/sign_in']")).click();
        //Enter invalid Email
        driver.findElement(By.id("user[email]")).sendKeys("johnson123@gmail.com");
        // Enter invalid password
        driver.findElement(By.name("user[password]")).sendKeys("johnson123");
        // Click on Sign in button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Verify the error message ‘Invalid email or password.’
        String expectedMessage = "Invalid email or password.";
        WebElement actualMessageElement = driver.findElement(By.xpath("//li[@class='form-error__list-item']"));
        String actualMessage = actualMessageElement.getText();
        Assert.assertEquals("Message not displayed", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
