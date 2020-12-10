import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

/**
 * @author gerben
 * @author Mats
 */

public class ContactOverviewTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\semester 2\\web2\\chromedriver.exe");
        driver = new ChromeDriver();
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("admin.ucll.be","t");

    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Contact_arwen_piot_kan_gezien_worden_door_admin() {
        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(contactsPage.containsBezoek("arwen piot","2020-11-09","12:12:00"));
    }
}
