import org.example.MTSPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class MTSTest {
    WebDriver driver;
    WebDriverWait wait;
    MTSPage MTSPage;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        MTSPage = new MTSPage(driver);
        WebElement click = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        click.click();

    }

    @Test(description = "1.1. Проверить placeholder надписи во всех полях 'Услуги связи'")
    public void testServicePlaceholders() {
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.servicePhoneInput, "Номер телефона"), "Placeholder для телефона 'Услуги связи' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.serviceSumInput, "Сумма"), "Placeholder для суммы 'Услуги связи' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.serviceEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Услуги связи' не отображается корректно");
    }

    @Test(description = "1.2. Проверить placeholder надписи во всех полях 'Домашний интернет'")
    public void testInternetPlaceholders() {
        MTSPage.selectOption(MTSPage.internetOption);
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.internetPhoneInput, "Номер абонента"), "Placeholder для телефона 'Домашний интернет' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.internetSumInput, "Сумма"), "Placeholder для суммы 'Домашний интернет' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.internetEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Домашний интернет' не отображается корректно");
    }

    @Test(description = "1.3. Проверить placeholder надписи во всех полях 'Рассрочка'")
    public void testInstallmentPlaceholders() {
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.installmentScoreInput, "Номер счета на 44"), "Placeholder для счета 'Рассрочка' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.installmentSumInput, "Сумма"), "Placeholder для суммы 'Рассрочка' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.installmentEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Рассрочка' не отображается корректно");
    }

    @Test(description = "1.4 Проверить placeholder надписи во всех полях 'Задолженность'")
    public void testDebtPlaceholders() {
        MTSPage.selectOption(MTSPage.debtOption);
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.debtScoreInput, "Номер счета на 2073"), "Placeholder для счета 'Задолженность' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.debtSumInput, "Сумма"), "Placeholder для суммы 'Задолженность' не отображается корректно");
        Assert.assertTrue(MTSPage.isPlaceholderDisplayed(MTSPage.debtEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Задолженность' не отображается корректно");
    }

    @Test(description = "2. Проверка полей 'Услуги связи' и отображение данных в окне")
    public void testServicePayment() {
        String phoneNumber = "297777777";
        String amount = "100.00";

        MTSPage.selectServiceOption();
        MTSPage.fillServiceForm(phoneNumber, amount);
        MTSPage.submitServiceForm();

        MTSPage.switchToPaymentFrame();

        Assert.assertTrue(MTSPage.isSummaryCostElementPresent(), "Элемент с суммой не найден");
        Assert.assertEquals(MTSPage.getSummaryCost(), amount + " BYN", "Сумма отображается некорректно");
        Assert.assertTrue(MTSPage.isSummaryPhoneElementPresent(), "Элемент с номером телефона не найден");
        Assert.assertTrue(MTSPage.getSummaryPhone().contains("Номер:375" + phoneNumber), "Номер телефона отображается некорректно");
        Assert.assertTrue(MTSPage.isPaymentIconsDisplayed(), "Иконки платёжных систем не отображаются");
        Assert.assertTrue(MTSPage.isPayButtonElementPresent(), "Кнопка оплаты не найдена");
        Assert.assertTrue(MTSPage.getPayButtonText().contains("Оплатить " + amount + " BYN"), "Сумма на кнопке некорректна");
        Assert.assertTrue(MTSPage.areCardPlaceholdersDisplayed(), "Placeholder для полей реквизитов карты не отображаются");
    }

    @AfterMethod
    void tearDown(){
        driver.navigate().refresh();
    }

    @AfterTest
    void setDown() {
        driver.quit();
        driver = null;
    }
}
