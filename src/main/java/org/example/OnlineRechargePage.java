package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OnlineRechargePage {
    private WebDriver driver;

    // Локаторы
    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение') and contains(text(), 'без комиссии')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[@class='pay__partners']//img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[@href='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']")
    private WebElement serviceLink;

    @FindBy(id = "phone-number-input")
    private WebElement phoneNumberInput;

    @FindBy(id = "continue-button")
    private WebElement continueButton;

    @FindBy(id = "next-page-element")
    private WebElement nextPageElement;

    public OnlineRechargePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isBlockTitleDisplayed() {
        return blockTitle.isDisplayed();
    }

    public boolean arePaymentLogosDisplayed() {
        String[] logosAltTexts = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String altText : logosAltTexts) {
            boolean isDisplayed = paymentLogos.stream().anyMatch(logo -> logo.getAttribute("alt").equals(altText) && logo.isDisplayed());
            if (!isDisplayed) {
                return false;
            }
        }
        return true;
    }

    public void clickServiceLink() {
        serviceLink.click();
    }

    public boolean isServiceLinkNavigated() {
        return driver.getCurrentUrl().contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    public void fillPhoneNumberAndSubmit(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
        continueButton.click();
    }

    public WebElement getNextPageElement() {
        return nextPageElement;
    }
}
