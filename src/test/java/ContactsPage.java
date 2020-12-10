import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * @author gerben
 * @author Mats
 */
public class ContactsPage extends Page {
    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Contacts");
    }
    public boolean containsBezoek(String naam,String datum,String tijd){
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(naam) &&  listItem.getText().contains(datum) &&  listItem.getText().contains(tijd)) {
                found=true;
            }
        }
        return found;
    }

}
