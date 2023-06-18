package naukariVisuals.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import setUp.GenericUtils;

import java.time.Duration;

public class NaukariHomePage {
    WebDriver driver;
    WebDriverWait wait;
    GenericUtils genericUtils;

    public NaukariHomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        genericUtils = new GenericUtils(driver);
    }

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    @FindBy(xpath = "//span[text()='Search jobs here']")
    private WebElement serchJobHere_SearchBar;
    @FindBy(xpath = "//input[@placeholder='Enter keyword / designation / companies']")
    private WebElement enterKeyWord_SearchBar;
    @FindBy(xpath = "//span[text()='Search']")
    private WebElement search_Button;
    @FindBy(xpath = "//div[@class='h1-wrapper']/span")
    private WebElement jobCount_text;


    public void click_SearchJobHere() {
        genericUtils.clickElement(serchJobHere_SearchBar);
    }
    public void click_EnterKeyWord() {
        genericUtils.clickElement(enterKeyWord_SearchBar);
    }
    public void click_Search(){
        genericUtils.clickElement(search_Button);
    }
    public void sendKeys_KeyWord(String designation) {
        genericUtils.sendKeys(enterKeyWord_SearchBar,designation);
    }

    public int getJobCount() {
        int FinalCount;
        String text = genericUtils.getText(jobCount_text);
        String[] ArrOfString = text.split("of",2);
        String ValueTwo = ArrOfString[1];
        FinalCount = Integer.parseInt(ValueTwo.trim());
        return FinalCount;
    }
}
