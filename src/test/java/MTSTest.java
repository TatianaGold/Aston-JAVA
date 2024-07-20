import org.example.RechargePage;
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
    RechargePage rechargePage;

    @BeforeClass
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        rechargePage = new RechargePage(driver);
        WebElement click = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        click.click();

    }

    @Test(description = "1.1. Проверить placeholder надписи во всех полях 'Услуги связи'")
    public void testServicePlaceholders() {
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.servicePhoneInput, "Номер телефона"), "Placeholder для телефона 'Услуги связи' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.serviceSumInput, "Сумма"), "Placeholder для суммы 'Услуги связи' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.serviceEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Услуги связи' не отображается корректно");
    }

    @Test(description = "1.2. Проверить placeholder надписи во всех полях 'Домашний интернет'")
    public void testInternetPlaceholders() {
        rechargePage.selectOption(rechargePage.internetOption);
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.internetPhoneInput, "Номер абонента"), "Placeholder для телефона 'Домашний интернет' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.internetSumInput, "Сумма"), "Placeholder для суммы 'Домашний интернет' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.internetEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Домашний интернет' не отображается корректно");
    }

    @Test(description = "1.3. Проверить placeholder надписи во всех полях 'Рассрочка'")
    public void testInstallmentPlaceholders() {
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.installmentScoreInput, "Номер счета на 44"), "Placeholder для счета 'Рассрочка' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.installmentSumInput, "Сумма"), "Placeholder для суммы 'Рассрочка' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.installmentEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Рассрочка' не отображается корректно");
    }

    @Test(description = "1.4 Проверить placeholder надписи во всех полях 'Задолженность'")
    public void testDebtPlaceholders() {
        rechargePage.selectOption(rechargePage.debtOption);
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.debtScoreInput, "Номер счета на 2073"), "Placeholder для счета 'Задолженность' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.debtSumInput, "Сумма"), "Placeholder для суммы 'Задолженность' не отображается корректно");
        Assert.assertTrue(rechargePage.isPlaceholderDisplayed(rechargePage.debtEmailInput, "E-mail для отправки чека"), "Placeholder для email 'Задолженность' не отображается корректно");
    }

    @Test(description = "2. Проверка полей 'Услуги связи' и отображение данных в окне")
    public void testServicePayment() {
        String phoneNumber = "297777777";
        String amount = "100.00";

        rechargePage.selectServiceOption();
        rechargePage.fillServiceForm(phoneNumber, amount);
        rechargePage.submitServiceForm();

        rechargePage.switchToPaymentFrame();

        Assert.assertTrue(rechargePage.isSummaryCostElementPresent(), "Элемент с суммой не найден");
        Assert.assertEquals(rechargePage.getSummaryCost(), amount + " BYN", "Сумма отображается некорректно");
        Assert.assertTrue(rechargePage.isSummaryPhoneElementPresent(), "Элемент с номером телефона не найден");
        Assert.assertTrue(rechargePage.getSummaryPhone().contains("Номер:375" + phoneNumber), "Номер телефона отображается некорректно");
        Assert.assertTrue(rechargePage.isPaymentIconsDisplayed(), "Иконки платёжных систем не отображаются");
        Assert.assertTrue(rechargePage.isPayButtonElementPresent(), "Кнопка оплаты не найдена");
        Assert.assertTrue(rechargePage.getPayButtonText().contains("Оплатить " + amount + " BYN"), "Сумма на кнопке некорректна");
        Assert.assertTrue(rechargePage.areCardPlaceholdersDisplayed(), "Placeholder для полей реквизитов карты не отображаются");
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
