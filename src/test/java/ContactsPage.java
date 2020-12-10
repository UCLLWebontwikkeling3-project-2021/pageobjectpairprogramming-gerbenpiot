import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

/**
 * @author gerben
 * @author Mats
 */
public class ContactsPage extends Page {
    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Contacts");
    }

    public boolean containsBezoekWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsBezoekWithDate(String date) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(date)) {
                found=true;
            }
        }
        return found;
    }

    public boolean containsBezoekWithTime(String time) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("td"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(time)) {
                found=true;
            }
        }
        return found;
    }
}
