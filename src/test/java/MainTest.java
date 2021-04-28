
import com.google.common.io.Files;
import com.sun.xml.internal.ws.policy.AssertionSet;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.nashorn.internal.AssertsEnabled;
import org.apache.hc.core5.util.Asserts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.util.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainTest extends BasePage {
    FileInputStream fis;
    Properties property = new Properties();
    public String email;
    public String password;
    public String url;
    public static LoginPage loginPage;
    public static PersonalPage personalPage;

    public MainTest() {
    }

    @Test
    public void loginTest() {

        try {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);
        email = property.getProperty("email");
        password = property.getProperty("password");
        url = property.getProperty("url");
    } catch (IOException e) {
        System.err.println("ОШИБКА: Файл свойств отсуствует!");
    }
//- Открыть https://otus.ru

     loginPage=new LoginPage();

 //- Авторизоваться на сайте
       Auth();

 //       - Войти в личный кабинет

        loginPage.Click(loginPage.personalRoom);
        loginPage.Click(loginPage.personalData);

        personalPage=new PersonalPage(driver);

      /* - В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
         - Нажать сохранить */
      personalPage.personalDataFill();

   //*- Открыть https://otus.ru в "чистом браузере"
   /*   setDown();
      loginPage=new LoginPage();

//- Авторизоваться на сайте
        Auth();

//- Войти в личный кабинет
        personalPage=new PersonalPage(driver);

//- Проверить, что в разделе "О себе" отображаются указанные ранее данные

      personalPage.FnameLatinGot=personalPage.GetData(personalPage.inputfnamelatin);
      Assert.assertEquals(personalPage.FnameLatinGot,personalPage.FnameLatin);

      personalPage.LnameLatinGot=personalPage.GetData(personalPage.inputlnamelatin);
      Assert.assertEquals(personalPage.LnameLatinGot,personalPage.LnameLatin);

      personalPage.DateOfBirthGot=personalPage.GetData(personalPage.date_of_birthInput);
      Assert.assertEquals(personalPage.DateOfBirthGot,personalPage.DateOfBirth);

      personalPage.ScrollDown("500");

      personalPage.countryGot=personalPage.GetData(personalPage.selectCountry);
      Assert.assertEquals(personalPage.countryGot,personalPage.countrySetText);

      personalPage.cityGot=personalPage.GetData(personalPage.citySet);
      Assert.assertEquals(personalPage.cityGot,personalPage.citySetText);

      personalPage.EnglishLevelGot=personalPage.GetData(personalPage.EnglishLevelSet);
      Assert.assertEquals(personalPage.EnglishLevelGot,personalPage.EnglishLevelText);

      personalPage.contact0Got=personalPage.GetData(personalPage.contact0);
      Assert.assertEquals(personalPage.contact0Got,email);

      personalPage.contact1Got=personalPage.GetData(personalPage.contact1);
      Assert.assertEquals(personalPage.contact1Got,email);
*/
    }
    private void Auth(){
        loginPage.openPage(url);
        loginPage.EnterRegButton();
        loginPage.typeEmail(email);
        loginPage.typePassword(password);
        loginPage.submitLogin();

    }
    }
