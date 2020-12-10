import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AantallenTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\semester 2\\web2\\chromedriver.exe");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        //System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
        driver = new ChromeDriver();
        driver.get(path+"?command=aantallenForm");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_aantallen_from_not_filled_in_gives_error_message(){
        submitForm("","02-11-2020");
        String title = driver.getTitle();
        assertEquals("aantallenForm",title);
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("de van datum is leeg.", errorMsg.getText());
        WebElement fieldLastName=driver.findElement(By.id("to"));
        assertEquals("2020-11-02",fieldLastName.getAttribute("value"));
    }
    @Test
    public void test_aantallen_to_not_filled_in_gives_error_message(){
        submitForm("02-11-2020","");
        String title = driver.getTitle();
        assertEquals("aantallenForm",title);
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("de tot datum is leeg.", errorMsg.getText());
        WebElement fieldLastName=driver.findElement(By.id("from"));
        assertEquals("2020-11-02",fieldLastName.getAttribute("value"));
    }
    @Test
    public void test_command_Formaantallen_geeft_juiste_pagina(){

        String title = driver.getTitle();
        assertEquals("aantallenForm",title);

    }
    @Test
    public void test_aantallen_from_and_to_geeft_aantallen_pagina(){
        submitForm("02-11-2020","02-11-2020");
        String title = driver.getTitle();
        assertEquals("aantallen",title);

    }
    @Test
    public void test_aantallen_from_and_to_geeft_aantallen_pagina_met_data(){
        submitForm("02-11-2020","04-11-2020");
        String title = driver.getTitle();
        assertEquals("aantallen",title);
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        int aantal =0;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("2020-11-02") ) {
                aantal++;
            }
            if (listItem.getText().contains("2020-11-03") ) {
                aantal++;
            }
            if (listItem.getText().contains("2020-11-04") ) {
                aantal++ ;
            }
        }
        assertEquals(3,aantal);

    }



    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String from,String to) {

        fillOutField("from", from);
        fillOutField("to",to);


        WebElement button=driver.findElement(By.id("aantal"));
        button.click();
    }
}
