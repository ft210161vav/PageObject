
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    protected static WebDriver driver;
    protected final Logger logger = LogManager.getLogger("BaseClass.class");

    public BaseClass(WebDriver driver) {
    }

    @BeforeAll
    public  static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
                driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }

        public WebElement $(By locator){
        return driver.findElement(locator);
    }

            @AfterAll
    public  static void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
