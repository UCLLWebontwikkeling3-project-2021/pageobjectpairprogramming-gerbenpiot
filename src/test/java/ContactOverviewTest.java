import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author gerben
 * @author Mats
 */

public class ContactOverviewTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";
    private static final String PREFS = "prefs";
    private static final String JAVASCRIPT_SETTINGS =
            "profile.managed_default_content_settings.javascript";
    private static final short DISABLED = 2;

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\semester 2\\web2\\chromedriver.exe");
        driver = new ChromeDriver(getChromeOptions());


    }
    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption(PREFS, getChromePrefs());
        return options;
    }

    private Map<String, Object> getChromePrefs() {
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(JAVASCRIPT_SETTINGS, DISABLED);
        return chromePrefs;
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Contacten_kan_gezien_worden_door_admin() {
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("admin@ucll.be","t");

        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertTrue(contactsPage.containsBezoek("greet jongen","2020-12-13","12:06:00"));
        assertTrue(contactsPage.containsBezoek("rene piot","2020-12-20","16:55:00"));
        assertTrue(contactsPage.containsBezoek("ilke willems","2020-12-07","14:15:00"));
        assertTrue(contactsPage.containsBezoek("jan Leukemans","2020-12-07","06:45:00"));


    }
    @Test
    public void test_Contacten_kan_gezien_worden_door_gerben() {
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("gerben.piot@gmail.com","gerben");

        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);

        assertTrue(contactsPage.containsBezoek("greet jongen","2020-12-13","12:06:00"));
        assertFalse(contactsPage.containsBezoek("rene piot","2020-12-20","16:55:00"));
        assertFalse(contactsPage.containsBezoek("ilke willems","2020-12-07","14:15:00"));
        assertFalse(contactsPage.containsBezoek("jan Leukemans","2020-12-07","06:45:00"));

    }
    @Test
    public void test_Contacten_kan_gezien_worden_door_gert() {
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("gert.piot@telenet.be","gert");

        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertFalse(contactsPage.containsBezoek("greet jongen","2020-12-13","12:06:00"));
        assertTrue(contactsPage.containsBezoek("rene piot","2020-12-20","16:55:00"));
        assertFalse(contactsPage.containsBezoek("ilke willems","2020-12-07","14:15:00"));
        assertFalse(contactsPage.containsBezoek("jan Leukemans","2020-12-07","06:45:00"));

    }
    @Test
    public void test_Contacten_kan_gezien_worden_door_arwen() {
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("arwen.piot@telenet.be","arwen");

        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertFalse(contactsPage.containsBezoek("greet jongen","2020-12-13","12:06:00"));
        assertFalse(contactsPage.containsBezoek("rene piot","2020-12-20","16:55:00"));
        assertTrue(contactsPage.containsBezoek("ilke willems","2020-12-07","14:15:00"));
        assertFalse(contactsPage.containsBezoek("jan Leukemans","2020-12-07","06:45:00"));

    }
    @Test
    public void test_Contacten_kan_gezien_worden_door_marijke() {
        IndexPage indexPage = PageFactory.initElements(driver,IndexPage.class);
        indexPage.logIn("Marijke.Leukemans@telenet.be","marijke");

        ContactsPage contactsPage = PageFactory.initElements(driver, ContactsPage.class);
        assertFalse(contactsPage.containsBezoek("greet jongen","2020-12-13","12:06:00"));
        assertFalse(contactsPage.containsBezoek("rene piot","2020-12-20","16:55:00"));
        assertFalse(contactsPage.containsBezoek("ilke willems","2020-12-07","14:15:00"));
        assertTrue(contactsPage.containsBezoek("jan Leukemans","2020-12-07","06:45:00"));

    }
}
