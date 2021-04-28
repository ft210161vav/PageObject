
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class PersonalPage extends BasePage
    {
    protected String DateOfBirth,DateOfBirthGot,FnameLatin,FnameLatinGot,LnameLatin,LnameLatinGot,countryGot,cityGot;
    protected String email,url,EnglishLevelGot,contact0Got,contact1Got,countrySetText,citySetText,EnglishLevelText;
    protected WebDriverWait wait;

        FileInputStream fis;
    Properties property = new Properties();
    public PersonalPage(WebDriver driver) {
                PageFactory.initElements(driver, this);
                wait = new WebDriverWait(driver, 8);

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            url = property.getProperty("url");
            email=property.getProperty("email");
            FnameLatin=property.getProperty("fnameLatin");
            LnameLatin=property.getProperty("lnameLatin");
            DateOfBirth = property.getProperty("DateOfBirth");
          } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }



        @FindBy(xpath = "//input[@name='country']/../div")
        private WebElement countryField;

        @FindBy(xpath = "//input[@name='country']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
        private List<WebElement> countryList;

        @FindBy(xpath = "//input[@name='city']/../div")
        private WebElement cityField;

        @FindBy(xpath = "//input[@name='city']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
        private List<WebElement> cityList;

        @FindBy(xpath = "//input[@name='english_level']/../div")
        private WebElement languageLevelField;

        @FindBy(xpath = "//input[@name='english_level']/../..//div[contains(@class, 'lk-cv-block__select-scroll')]/button")
        private List<WebElement> languageLevelList;

        @FindBy(xpath = "//input[@name='contact-0-service']/../div")
        private WebElement selectContact;

        @FindBy(xpath = "//input[@name='contact-0-service']/../..//div[contains(@class, 'js-custom-select-input')]/button")
        private List<WebElement> selectContactList;

        @FindBy(xpath = "//button[@title='Skype']/../div")
        private WebElement selectSkypeButton;

        @FindBy(xpath = "//button[@title='Skype']/../..//div[contains(@class," +
                "'lk-cv-block__select-scroll.lk-cv-block__select-scroll_service.js-custom-select-options')]")
        private List<WebElement> selectSkypeButtonList;

        //////////////////////////////////////////////////////
    @FindBy(name ="date_of_birth" )
    protected   WebElement date_of_birthInput;

    @FindBy(id ="id_fname_latin" )
    protected WebElement inputfnamelatin;

    @FindBy(id ="id_lname_latin" )
    protected WebElement inputlnamelatin;

    @FindBy(css ="[data-ajax-slave=\"/lk/biography/cv/lookup/cities/by_country/\"]")
            private WebElement selectCountry;

   @FindBy(xpath = "//div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[1]/div[2]/div/div/div/button[5]")
            private WebElement country;

    @FindBy(className ="container__row")
    private WebElement selectCity;

    @FindBy(xpath = "//form/div[1]/div[3]/div[1]/div/div[1]/div[2]/div[2]/div/div/div/button[8]")
    private WebElement city;

    @FindBy(xpath = "/html/body/div[2]/div/div[5]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[1]/div/div[1]/div[3]/div[2]/div/div")
    private WebElement selectEnglishLevel;

        WebElement levelDropDown = driver.findElement(By.xpath("//*[@name=\"english_level\"]/.."));
        WebElement EnglishLevel=driver.findElement(By.cssSelector("[title=\"Средний (Intermediate)\"]"));

    @FindBy(css = "button.lk-cv-block__select-option.js-custom-select-option[title=\"Skype\"]")
    protected WebElement Skype;

    @FindBy(css = "button[title='WhatsApp']")
    private WebElement WhatsApp;

    @FindBy(xpath = "//form/div[1]/div[3]/div[2]/div[2]/button")
    private WebElement addContact;

    @FindBy(id = "id_contact-0-value")
    protected WebElement contact0;

    @FindBy(id = "id_contact-1-value")
    protected WebElement contact1;

    @FindBy(css="[title='Сохранить и продолжить']")
    private WebElement saveButton;
///////////////////////////////////////////////////////

        private void setDivSelector(List<WebElement> elements, String expectedValue) {
    try {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements))
                .stream()
                .filter(element -> element.getText().equalsIgnoreCase(expectedValue))
                .findFirst().get().click();
    } catch (java.util.NoSuchElementException e) {
        throw new NoSuchElementException(String.format("Not found list element with value: %s", expectedValue));
    }

}

        public void setCountry(String country) {
            logger.info(String.format("Заполнить страну: %s", country));
            wait.until(ExpectedConditions.visibilityOf(countryField)).click();
            setDivSelector(countryList, country);
        }

        public void setCity(String city) {
            logger.info(String.format("Заполнить город: %s", city));
// Need to load city list before click
            wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(cityField, By.cssSelector("span")));
            cityField.click();
            setDivSelector(cityList, city);
        }

        public void setLanguageLevel(String languageLevel) {
            logger.info(String.format("Заполнить уровень английского: %s", languageLevel));
            wait.until(ExpectedConditions.visibilityOf(languageLevelField)).click();
            setDivSelector(languageLevelList, languageLevel);
        }

        public void selectContactService(String selectContactText) {
            logger.info(String.format("Выбрать способ связи: %s", selectContactText));
            wait.until(ExpectedConditions.visibilityOf(selectContact)).click();
            setDivSelector(selectContactList,selectContactText);
        }

        public void selectSkype() {
            logger.info(String.format("Выбрать способ связи: %s", "Skype"));
            wait.until(ExpectedConditions.visibilityOf(selectSkypeButton)).click();
            setDivSelector(selectSkypeButtonList,"Skype");
        }


        //////////////////////////////////////////////////////
    public void personalDataFill() {
        input(inputfnamelatin, FnameLatin);
        input(inputlnamelatin, LnameLatin);
        input(date_of_birthInput, DateOfBirth);

        scrollDown("500");
        click(selectCountry);
        click(country);
        countrySetText=getData(selectCountry);

        click(selectCity);
        click(city);
        citySetText=getData(selectCity);

        setLanguageLevel("Средний (Intermediate)");
        EnglishLevelText=getData(languageLevelField);

        scrollDown("500");
        selectContactService("Способ связи");

        click(Skype);
        input(contact0, email);

        click(addContact);
        click(selectContact);
        click(WhatsApp);
        input(contact1,email);

        click(saveButton);

    }
      private void input (WebElement inputLocator, String inputData){
         new WebDriverWait(driver, 10).
                until(ExpectedConditions.elementToBeClickable(inputLocator));
        Actions action = new Actions(driver);
        action.moveToElement(inputLocator).perform();
         inputLocator.sendKeys(inputData);
    }

        protected String getData (WebElement inputLocator){
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.elementToBeClickable(inputLocator));
            Actions action = new Actions(driver);
            action.moveToElement(inputLocator).perform();
            String gotData=inputLocator.getText();
            return gotData;
        }

        private void click (WebElement inputLocator){
            new WebDriverWait(driver, 15).
                    until(ExpectedConditions.elementToBeClickable(inputLocator));
            Actions action = new Actions(driver);
            action.moveToElement(inputLocator).click().build().perform();
        }

    protected void scrollDown (String pxls) {
        JavascriptExecutor je =(JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,"+pxls+")");
        }
    private void makeScreenshot() throws Exception {
        TakesScreenshot screenshotShot = ((TakesScreenshot) driver);
        File SrcFile = screenshotShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("C:/mdistr/1.png");
        Files.copy(SrcFile, DestFile);
    }

    }


