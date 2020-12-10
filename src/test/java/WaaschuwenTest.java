import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WaaschuwenTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/ContactTracing_war_exploded/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\semester 2\\web2\\chromedriver.exe");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        //System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
        driver.get(path+"?command=NotifyForm");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_waarschuw_voornaam_not_filled_in_gives_error_message(){
        submitForm("","piot");
        String title = driver.getTitle();
        assertEquals("waarschuw personen",title);
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("voornaam mag niet leeg zijn", errorMsg.getText());
        WebElement fieldLastName=driver.findElement(By.id("familienaam"));
        assertEquals("piot",fieldLastName.getAttribute("value"));
    }
    @Test
    public void test_waarschuw_familienaam_not_filled_in_gives_error_message(){
        submitForm("gerben","");
        String title = driver.getTitle();
        assertEquals("waarschuw personen",title);
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("familienaam mag niet leeg zijn", errorMsg.getText());
        WebElement fieldFirstName=driver.findElement(By.id("voornaam"));
        assertEquals("gerben",fieldFirstName.getAttribute("value"));
    }
    @Test
    public void test_command_FormNotify_geeft_juiste_pagina(){

        String title = driver.getTitle();
        assertEquals("waarschuw personen",title);

    }
    @Test
    public void test_waarschuw_familienaam_and_voornaam_in_lowercase_geeft_notify_pagina(){
        submitForm("gerben","piot");
        String title = driver.getTitle();
        assertEquals("overzicht in contact gekomen",title);

    }
    @Test
    public void test_waarschuw_familienaam_and_voornaam_in_uppercase_geeft_notify_pagina(){
        submitForm("GERBEN","PIOT");
        String title = driver.getTitle();
        assertEquals("overzicht in contact gekomen",title);

    }
    @Test
    public void test_waarschuw_familienaam_in_lowercase_and_voornaam_in_uppercase_geeft_notify_pagina(){
        submitForm("GERBEN","piot");
        String title = driver.getTitle();
        assertEquals("overzicht in contact gekomen",title);

    }
    @Test
    public void test_waarschuw_familienaam_in_uppercase_and_voornaam_in_lowercase_geeft_notify_pagina(){
        submitForm("gerben","PIOT");
        String title = driver.getTitle();
        assertEquals("overzicht in contact gekomen",title);

    }


    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String firstName,String lastName) {

        fillOutField("voornaam", firstName);
        fillOutField("familienaam",lastName);


        WebElement button=driver.findElement(By.id("Notify"));
        button.click();
    }
}
