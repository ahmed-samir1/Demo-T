package login_Task;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class login_Test {

    protected static WebDriver driver;


    @BeforeEach
    public void startWebDriver() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    public void quitWebDriver() {
        driver.quit();
    }

    @Test
    public void testLogin() {
        driver.get("http://www.testyou.in/Login.aspx ");

        driver.findElement(By.id("ctl00_CPHContainer_txtUserLogin")).sendKeys("Ahmed@gmail.com");
        driver.findElement(By.id("ctl00_CPHContainer_txtPassword")).sendKeys("hi123456");
        driver.findElement(By.id("ctl00_CPHContainer_btnLoginn")).click();

        assertEquals(
                "Userid or Password did Not Match !!",
                driver.findElement(By.id("ctl00_CPHContainer_lblOutput")).getText());
    }
}

