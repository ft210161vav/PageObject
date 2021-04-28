
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage extends BaseClass{


    public BasePage() {
        super(driver);

        PageFactory.initElements(driver,this);

    }
  public BasePage openPage(String url){
        driver.get(url);
        return this;
   }

        }


