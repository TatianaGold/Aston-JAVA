package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MTSPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = ".select__header")
    private WebElement selectHeader;

//    @FindBy(xpath = "//li[@class='select__item']/p[text()='Услуги связи']")
//    public WebElement servicesOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Домашний интернет']")
    public WebElement internetOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Рассрочка']")
    public WebElement installmentOption;

    @FindBy(xpath = "//li[@class='select__item']/p[text()='Задолженность']")
    public WebElement debtOption;

    @FindBy(id = "connection-phone")
    public WebElement servicePhoneInput;

    @FindBy(id = "connection-sum")
    public WebElement serviceSumInput;

    @FindBy(id = "connection-email")
    public WebElement serviceEmailInput;

    @FindBy(id = "internet-phone")
    public WebElement internetPhoneInput;

    @FindBy(id = "internet-sum")
    public WebElement internetSumInput;

    @FindBy(id = "internet-email")
    public WebElement internetEmailInput;

    @FindBy(id = "score-instalment")
    public WebElement installmentScoreInput;

    @FindBy(id = "instalment-sum")
    public WebElement installmentSumInput;

    @FindBy(id = "instalment-email")
    public WebElement installmentEmailInput;

    @FindBy(id = "score-arrears")
    public WebElement debtScoreInput;

    @FindBy(id = "arrears-sum")
    public WebElement debtSumInput;

    @FindBy(id = "arrears-email")
    public WebElement debtEmailInput;

    @FindBy(xpath = "//form[@id='pay-connection']//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='pay-description__cost']/span")
    public WebElement summaryCostElement;

    @FindBy(xpath = "//div[@class='pay-description__text']/span")
    public WebElement summaryPhoneElement;

    @FindBy(id = "gpay-button-online-api-id")
    public WebElement googlePayButton;

    @FindBy(id = "yandex-button")
    public WebElement yandexPayButton;

    @FindBy(xpath = "//button[@type='submit' and contains(text(), 'Оплатить')]")
    public WebElement payButton;

    @FindBy(id = "cc-number")
    public WebElement cardNumberInput;

    @FindBy(xpath = "//input[@formcontrolname='expirationDate']")
    public WebElement expirationDateInput;

    @FindBy(xpath = "//input[@formcontrolname='cvc']")
    public WebElement cvcInput;

    @FindBy(xpath = "//input[@formcontrolname='holder']")
    public WebElement cardHolderInput;

    public MTSPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void selectOption(WebElement option) {
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public boolean isPlaceholderDisplayed(WebElement inputElement, String expectedPlaceholder) {
        return inputElement.getAttribute("placeholder").equals(expectedPlaceholder);
    }

    public void selectServiceOption() {
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();
    }

    public void switchToPaymentFrame() {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']")));
    }

    public void fillServiceForm(String phoneNumber, String amount) {
        servicePhoneInput.clear();
        servicePhoneInput.sendKeys(phoneNumber);
        serviceSumInput.clear();
        serviceSumInput.sendKeys(amount);
    }
    public boolean isPaymentIconsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(googlePayButton));
        wait.until(ExpectedConditions.visibilityOf(yandexPayButton));
        return googlePayButton.isDisplayed() && yandexPayButton.isDisplayed();
    }

    public void submitServiceForm() {
        continueButton.click();
    }

    public String getSummaryCost() {
        return summaryCostElement.getText();
    }

    public String getSummaryPhone() {
        return summaryPhoneElement.getText();
    }

    public String getPayButtonText() {
        return payButton.getText();
    }

    public boolean isSummaryCostElementPresent() {
        return !driver.findElements(By.xpath("//div[@class='pay-description__cost']/span")).isEmpty();
    }

    public boolean isSummaryPhoneElementPresent() {
        return !driver.findElements(By.xpath("//div[@class='pay-description__text']/span")).isEmpty();
    }

    public boolean isPayButtonElementPresent() {
        return !driver.findElements(By.xpath("//button[@type='submit' and contains(text(), 'Оплатить')]")).isEmpty();
    }

    public boolean areCardPlaceholdersDisplayed() {
        return cardNumberInput.isDisplayed() &&
                expirationDateInput.isDisplayed() &&
                cvcInput.isDisplayed() &&
                cardHolderInput.isDisplayed();
    }
}


