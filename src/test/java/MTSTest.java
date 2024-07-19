import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.OnlineRechargePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;


public class MTSTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlineRechargePage onlineRechargePage;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        onlineRechargePage = new OnlineRechargePage(driver);
        WebElement click = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        click.click();
    }

    @Test(description = "Проверить название блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        Assert.assertTrue(onlineRechargePage.isBlockTitleDisplayed(), "Название блока 'Онлайн пополнение без комиссии' не найдено или не отображается");
    }

    @Test(description = "Проверить наличие логотипов платёжных систем")
    public void testPaymentLogosPresence() {
        Assert.assertTrue(onlineRechargePage.arePaymentLogosDisplayed(), "Логотипы платёжных систем не найдены или не отображаются");
    }

    @Test(description = "Проверить работу ссылки 'Подробнее о сервисе'")
    public void testServiceLink() {
        onlineRechargePage.clickServiceLink();
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
        Assert.assertTrue(onlineRechargePage.isServiceLinkNavigated(), "Переход по ссылке 'Подробнее о сервисе' не работает корректно");

        driver.navigate().back();
    }

    @Test(description = "Заполнить поля и проверить работу кнопки 'Продолжить'")
    public void testContinueButton() {
        onlineRechargePage.fillPhoneNumberAndSubmit("297777777");
        WebElement nextPageElement = wait.until(ExpectedConditions.visibilityOf(onlineRechargePage.getNextPageElement()));
        Assert.assertTrue(nextPageElement.isDisplayed(), "Кнопка 'Продолжить' не работает корректно");
    }

    @AfterTest
    void setDown() {
        driver.quit();
        driver = null;
    }
}