package linkedInVisuals.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import setUp.GenericUtils;

import java.time.Duration;

public class LinkedInHomePage {
    WebDriver driver;
    WebDriverWait wait;
    GenericUtils genericUtils;


    public LinkedInHomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        genericUtils = new GenericUtils(driver);
    }

    @FindBy(xpath = "//a[text()='Sign in']")
    private WebElement SignInLink;
    @FindBy(id = "username")
    private WebElement username;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement SignInButton;

    @FindBy(xpath ="//input[@class='search-global-typeahead__input']")
    private WebElement SearchBox;
    @FindBy(id="global-nav-typeahead")
    private WebElement seeAllResults;
    @FindBy(xpath = "//button[contains(string(),'Companies')]")
    private WebElement CompaniesFilter;
    @FindBy(xpath = "//a[contains(string(),'About')]")
    private WebElement AboutPage;
    @FindBy(xpath = "//dt[text()='Company size']")
    private WebElement CompanySize;

    @FindBy(xpath = "//dt[text()='Company size']/following-sibling::dd[1]")
    private WebElement EmployeeCount;
    @FindBy(xpath = "//dt[text()='Company size']/following-sibling::dd[2]")
    private WebElement EmployeeRange;
    public void Login(String UserName,String Password) {
        genericUtils.clickElement(SignInLink);
        genericUtils.sendKeys(username,UserName);
        genericUtils.sendKeys(password,Password);
        genericUtils.clickElement(SignInButton);
    }

    public void SearchCompany(String CompanyName) {
        genericUtils.sendKeys(SearchBox,CompanyName);
        genericUtils.clickEnter(SearchBox);
        genericUtils.clickElement(CompaniesFilter);
        WebElement CompanyTitleLocation = genericUtils.findElementByLinkText(CompanyName);
        genericUtils.clickElement(CompanyTitleLocation);
    }

    public void NavigateToCompanySize() {
        genericUtils.clickEnter(AboutPage);
        genericUtils.scrollTo(CompanySize);
    }


    public String GetLinkedInEmployeeCount() {
        String Count = null;
        String text = genericUtils.getText(EmployeeCount);
        if(text.contains("+")){
            String NewText = text.replace("+", "!");
            String[] ArrOfString = NewText.split("!",2);
            String ValueOne = ArrOfString[0];
            String NewValue = ValueOne.replace(",", "");
            Count = NewValue.trim()+"+";
        } else if (text.contains("-")) {
            String[] ArrOfString = text.split(" ",2);
            String ValueOne = ArrOfString[0];
            Count = ValueOne.trim();
        }else {
            System.out.println("Error");
        }
        return Count;
    }

    // Employees on linked in
    public int GetEmployeesOnLinkedIn() {
        int Count;
        String text = genericUtils.getText(EmployeeRange);
        String[] ArrOfString = text.split("on",2);
        String ValueOne = ArrOfString[0];
        String NewValue = ValueOne.replace(",", "");
        Count = Integer.parseInt(NewValue.trim());
        return Count;
    }
}
