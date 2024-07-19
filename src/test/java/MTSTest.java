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
import java.util.HashMap;
import java.util.Map;


public class MTSTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private Map<String, Map<String, String>> expectedPlaceholders;

    @BeforeClass
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement click = driver.findElement(By.xpath("//*[@id=\"cookie-agree\"]"));
        click.click();
    }


    @Test(description = "1. Проверка placeholder'ов в незаполненных полях для всех вариантов оплаты услуг")
    void placeholdersTest() {
        Map<String, Map<String, String>> expectedPlaceholders = new HashMap<>();
        // Настройка ожидаемых значений placeholder'ов для каждого варианта оплаты
        expectedPlaceholders.put("Услуги связи", Map.of("connection-phone", "Номер телефона", "connection-sum", "Сумма", "connection-email", "E-mail для отправки чека"));

        expectedPlaceholders.put("Домашний интернет", Map.of("internet-phone", "Номер абонента", "internet-sum", "Сумма", "internet-email", "E-mail для отправки чека"));

        expectedPlaceholders.put("Рассрочка", Map.of("score-instalment", "Номер счета на 44", "instalment-sum", "Сумма", "instalment-email", "E-mail для отправки чека"));

        expectedPlaceholders.put("Задолженность", Map.of("score-arrears", "Номер счета на 2073", "arrears-sum", "Сумма", "arrears-email", "E-mail для отправки чека"));

        // Проверка placeholder'ов для каждого варианта оплаты
        for (String option : expectedPlaceholders.keySet()) {
            selectOption(option);
            checkPlaceholders(expectedPlaceholders.get(option));
        }
    }

    private void selectOption(String option) {
        WebElement optionElement = driver.findElement(By.xpath("//option[@value='" + option + "']"));
        optionElement.click();
    }

    private void checkPlaceholders(Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            WebElement input = driver.findElement(By.id(entry.getKey()));
            String placeholder = input.getAttribute("placeholder");
            Assert.assertEquals(placeholder, entry.getValue(), "Ошибка в placeholder для поля: " + entry.getKey());
        }
    }

    @Test(description = "2. Проверка полей 'Услуги связи' и отображение суммы")
    void servicePaymentTest() {
        WebElement optionElement = driver.findElement(By.xpath("//option[@value='Услуги связи']"));
        optionElement.click();

        // Заполнение полей
        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        phoneInput.clear();
        String phoneNumber = "375297777777";
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.clear();
        String amount = "100.00";
        sumInput.sendKeys(amount);

        // Нажатие кнопки "Продолжить"
        WebElement continueButton = driver.findElement(By.xpath("//form[@id='pay-connection']//button[@type='submit']"));
        continueButton.click();

        // Ожидание появления фрейма и переключение в него
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='app-wrapper__content']")));

        // Проверка корректности отображения суммы
        WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay-description__cost']/span")));
        Assert.assertEquals(amountElement.getText(), amount + " BYN", "Сумма отображается некорректно");

        // Проверка корректности отображения номера телефона
        WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pay-description__text']/span")));
        Assert.assertTrue(phoneElement.getText().contains("Номер:" + phoneNumber), "Номер телефона отображается некорректно");

        // Проверка надписей в незаполненных полях для ввода реквизитов карты
        WebElement cardNumberLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Номер карты')]")));
        WebElement expirationDateLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Срок действия')]")));
        WebElement cvcLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'CVC')]")));
        WebElement cardHolderLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]")));

        Assert.assertTrue(cardNumberLabel.isDisplayed(), "Надпись 'Номер карты' не отображается");
        Assert.assertTrue(expirationDateLabel.isDisplayed(), "Надпись 'Срок действия' не отображается");
        Assert.assertTrue(cvcLabel.isDisplayed(), "Надпись 'CVC' не отображается");
        Assert.assertTrue(cardHolderLabel.isDisplayed(), "Надпись 'Имя держателя (как на карте)' не отображается");

        // Проверка корректности отображения суммы на кнопке "Оплатить"
        WebElement payButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'colored') and contains(@class, 'disabled')]")));
        String expectedAmountText = "Оплатить " + amount + " BYN";
        Assert.assertTrue(payButton.getText().contains(expectedAmountText), "Сумма на кнопке некорректна");
    }

    @AfterClass
    void setDown() {
        driver.quit();
        driver = null;
    }
}
