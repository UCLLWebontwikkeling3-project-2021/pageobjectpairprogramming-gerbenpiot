import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public IndexPage (WebDriver driver) {
        super(driver);
        this.driver.get(path);
    }

    public void logIn(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        logInButton.click();
    }
}
