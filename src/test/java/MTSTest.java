import io.github.bonigarcia.wdm.WebDriverManager;
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

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement click = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        click.click();
    }

    @Test(description = "1. Проверка названия блока")
    void titleTest() {
        WebElement title = driver.findElement(By.xpath("//h2[normalize-space(text())='Онлайн пополнение' and br/following-sibling::text()[normalize-space()='без комиссии']]"));
        Assert.assertNotNull(title, "Блок 'Онлайн пополнение без комиссии' не найден");
        String expected = "Онлайн пополнение без комиссии";
        String actualTitle = title.getText().replace("\n", " ").trim();
        Assert.assertEquals(actualTitle, expected, "Заголовок блока не совпадает с ожидаемым");
    }

    @DataProvider(name = "paymentLogos")
    public Object[][] paymentLogos() {
        return new Object[][]{
                {"Visa"},
                {"Verified By Visa"},
                {"MasterCard"},
                {"MasterCard Secure Code"},
                {"Белкарт"}
        };
    }

    @Test(dataProvider = "paymentLogos", description = "2. Проверка на наличие логотипов платежных систем")
    void paymentLogosTest(String altText) {
        WebElement logo = driver.findElement(By.xpath("//div[@class='pay__partners']//img[@alt='" + altText + "']"));
        Assert.assertTrue(logo.isDisplayed(), "Логотип платежной системы '" + altText + "' не найден");
    }

    @Test(description = "3. Проверка работы ссылки 'Подробнее о сервисе'")
    void serviceLinkTest() {
        WebElement serviceLink = driver.findElement(By.xpath("//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']"));
        Assert.assertTrue(serviceLink.isDisplayed(), "Ссылка 'Подробнее о сервисе' не найдена или не отображается");
        serviceLink.click();
        wait.until(ExpectedConditions.urlContains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));

        String expectedUrlPath = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrlPath), "Переход по ссылке 'Подробнее о сервисе' не работает корректно");

        driver.navigate().back();
    }

    @Test(description = "4. Проверка заполненных полей и кнопки 'Продолжить'")
    void continueButtonTest() {
//        WebElement selectElement = driver.findElement(By.id("pay"));
//        selectElement.click();

        WebElement optionElement = driver.findElement(By.xpath("//option[@value='Услуги связи']"));
        optionElement.click();

        // Заполнение полей
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        phoneInput.clear();

        phoneInput.sendKeys("297777777");
        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.clear();
        sumInput.sendKeys("100");

        // Нажатие кнопки "Продолжить"
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        continueButton.click();

        Assert.assertTrue(continueButton.isDisplayed(), "Кнопка 'Продолжить' не отображается.");
        Assert.assertTrue(continueButton.isEnabled(), "Кнопка 'Продолжить' не кликабельна.");
        continueButton.click();

        // Ожидание появления фрейма и переключение в него
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class=\"bepaid-iframe\"]")));
        WebElement modalWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("app-wrapper__content")));
        Assert.assertTrue(modalWindow.isDisplayed(), "Кнопка 'Продолжить' не работает корректно");
    }

    @AfterTest
    void setDown() {
        driver.quit();
        driver = null;
    }
}