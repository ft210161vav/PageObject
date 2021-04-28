
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage extends BasePage{

    @FindBy(className = "new-log-reg__text-item")
    private WebElement regTitle;

    @FindBy(className = "header2__right")
    private WebElement loginRegButton;

    @FindBy(css = "div[data-mode='login'] input[name='email']")
    private WebElement emailInput;

    By passwordLocator = By.name("password");
    @FindBy(css = "input[name='password'][type=\"password\"]")
    private WebElement passwordInput;

    @FindBy(css ="div[data-mode='login'] button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "[href='/learning/'")
    protected WebElement personalRoom;

    @FindBy(css = "div.nav__items [href='/lk/biography/personal/']")//header2-menu
    protected WebElement personalData;

    public LoginPage() {

        PageFactory.initElements(driver,this);

    }

    public LoginPage EnterRegButton() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(loginRegButton));
        Actions action = new Actions(driver);
        action.moveToElement(loginRegButton).build().perform();
        loginRegButton.click();
                   return this;
       }

     protected void Click(WebElement inputLocator){
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.elementToBeClickable(inputLocator));
        Actions action = new Actions(driver);
        action.moveToElement(inputLocator).click().build().perform();
    }
    protected LoginPage typeEmail(String email) {
        Actions action = new Actions(driver);
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(emailInput));
        action.moveToElement(emailInput).build().perform();
        emailInput.click();
        Click(emailInput);
        emailInput.sendKeys(email);
        logger.info(email);
        return this;
    }
       protected LoginPage typePassword(String password) {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(passwordLocator));
            Actions action = new Actions(driver);
            action.moveToElement(passwordInput).click().perform();
            passwordInput.sendKeys(password);
            return this;
        }

        protected LoginPage submitLogin() {
            new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(loginButton));
            Actions action = new Actions(driver);
            action.moveToElement(loginButton).perform();
            loginButton.submit();
            return this;

        }

        }


