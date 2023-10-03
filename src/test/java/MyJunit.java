import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class MyJunit {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // For Rejecting Privacy and Cookie Policy
    public void rejecthandler() {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-reject-all-handler")).click();
    }

    @DisplayName("WebForm Automation")
    @Test

    public void wenFormAutomation() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        rejecthandler();

        // Input Name
        driver.findElement(By.id("edit-name")).sendKeys("Shishir Saha");

        // Input Phone Number
        driver.findElement(By.id("edit-number")).sendKeys("01913228855");

        // Selecting Age Range Button
        Utils.scroll(driver, 0, 500);
        WebElement ageRangeBtnElement = driver.findElement(By.xpath("//label[contains(text(),'20-30')]"));
        ageRangeBtnElement.click();

        // Code for Picking Date

        Utils.scroll(driver, 0, 600);
        WebElement datePciElement = driver.findElement(By.xpath("//input[@id='edit-date']"));
        datePciElement.sendKeys("10/03/2023");
        
        // Input Email Address
        Utils.scroll(driver, 0, 700);
        WebElement inputEmaiElement = driver.findElement(By.id("edit-email"));
        inputEmaiElement.sendKeys("ShishirSaha1530@gmail.com");
       
        // Textarea Section
        Utils.scroll(driver, 0, 750);
        WebElement textareaElement = driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-"));
        textareaElement.sendKeys("Hi, this is Shishir Saha and I am learning JUnit testing");
       
        // File Uploading
        Utils.scroll(driver, 0, 750);
        WebElement fileUploadElement = driver.findElement(By.id("edit-uploadocument-upload"));
        fileUploadElement.sendKeys(System.getProperty("user.dir") + "/src/test/resources/google.png");
        Thread.sleep(5000);
    
        // Checkbox
        Utils.scroll(driver, 0, 950);
        WebElement checkBoxElement = driver.findElement(By.xpath("//input[@id='edit-age']"));
        checkBoxElement.click();
       
        // Submit Button
        WebElement suubmitBtnElement = driver.findElement(By.id("edit-submit"));
        suubmitBtnElement.click();

        // Confirmation Text Assertions
        String actualText = driver.findElement(By.xpath("//h1[normalize-space()='Thank you for your submission!']"))
                .getText();
        String expectedText = "Thank you for your submission!";
        Assertions.assertEquals(actualText, expectedText);

    }

    // Quit After Submitting the Form
    @AfterAll
    public void tearDown() {
        driver.quit();
    }

}
