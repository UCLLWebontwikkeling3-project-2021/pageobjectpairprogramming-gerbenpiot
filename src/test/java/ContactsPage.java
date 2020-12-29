import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

/**
 * @author gerben
 * @author Mats
 */
public class ContactsPage extends Page {
    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="datum")
    private WebElement dateField;

    @FindBy(id="tijd")
    private WebElement hourField;

    @FindBy(id="telefoon")
    private WebElement gsmField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="sign")
    private WebElement signUpButton;


    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }
    public boolean hasSuccesMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.succes ul li"));
        return errorMsg.getText().equals(message);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setDate(String date) {
        dateField.clear();
        dateField.sendKeys(date);
    }

    public void setHour(String hour) {
        hourField.clear();
        hourField.sendKeys(hour);
    }

    public void setGsm(String gsm) {
        gsmField.clear();
        gsmField.sendKeys(gsm);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void pressButton() {
        signUpButton.click();
    }

    public boolean hasErrorMessage (String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("#container > main > div > ul > li"));
        return (message.equals(errorMsg.getText()));
    }

    public boolean hasStickyFirstName (String firstname) {
        return firstNameField.getAttribute("value").equals(firstname);
    }

    public boolean hasStickyLastName (String lastname) {
        return lastNameField.getAttribute("value").equals(lastname);
    }

    public boolean hasStickyDate (String date) {
        return dateField.getAttribute("value").equals(date);
    }

    public boolean hasStickyHour (String hour) {
        return hourField.getAttribute("value").equals(hour+":00");
    }

    public boolean hasStickyGsm (String gsm) {
        return gsmField.getAttribute("value").equals(gsm);
    }

    public boolean hasStickyEmail (String email) {
        return emailField.getAttribute("value").equals(email);
    }

    public boolean hasEmptyFirstName () {
        return firstNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyLastName () {
        return lastNameField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyDate () {
        return dateField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyHour () {
        return hourField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyGsm () {
        return gsmField.getAttribute("value").isEmpty();
    }

    public boolean hasEmptyEmail () {
        return emailField.getAttribute("value").isEmpty();
    }

    public ContactsPage (WebDriver driver) {
        super(driver);
        this.driver.get(path+"?command=Contacts");
    }
    public boolean containsBezoek(String naam,String dag,String tijd){
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(naam) &&  listItem.getText().contains(dag) &&  listItem.getText().contains(tijd)) {
                found=true;
            }
        }
        return found;
    }
    
}
