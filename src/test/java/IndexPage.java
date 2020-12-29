import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author gerben
 * @author Mats
 */
public class IndexPage extends Page {
    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "logIn")
    private WebElement logInButton;
    @FindBy(id = "logOut")
    private WebElement logoutButton;
    @FindBy(css = "nav ul li")
    private List<WebElement> menuItems;

    public IndexPage (WebDriver driver) {
        super(driver);
        this.driver.get(path);
    }

    public void setEmailField(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public boolean hasErrorMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        return errorMsg.getText().equals(message);
    }
    public boolean hasSuccesMessage(String message) {
        WebElement errorMsg = driver.findElement(By.cssSelector("div.succes ul li"));
        return errorMsg.getText().equals(message);
    }

    public void logIn(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        logInButton.click();
    }
    public void logout(){
        logoutButton.click();
    }
    public boolean hasloginphase(String phrase){
        boolean has =false;
        for (WebElement element : driver.findElements(By.cssSelector("p"))){
            if (element.getText().equals(phrase)){
                has =true;
            }
        }
        return has;
    }
    public int getNumberOfMenuItems(){
        return menuItems.size();
    }
}
