package setUp;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericUtils {

    WebDriver driver;
    WebDriverWait wait;
    public GenericUtils(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void sendKeys(WebElement element, String value){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }
    public void clickElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }
    public String getText(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    public void clickEnter(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Keys.ENTER);
    }

    public WebElement findElementByLinkText(String element) {
        WebElement ele = driver.findElement(By.linkText(element));
        return ele;
    }

    public void scrollTo(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
