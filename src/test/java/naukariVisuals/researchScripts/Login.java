package naukariVisuals.researchScripts;

import naukariVisuals.pageObjects.HomePage;
import setUp.BaseClass;
import Utilities.DataUtil;
import Utilities.MyXLSReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;

public class Login extends BaseClass {
    WebDriver driver;
    MyXLSReader excelReader;


    @Test(dataProvider = "dataSupplier")
    public void loginTest(HashMap<String,String> hMap) throws InterruptedException {
        if(!DataUtil.isRunnable(excelReader,"LoginTest","Testcases") || hMap.get("Runmode").equals("N")){
            throw new SkipException("Run Mode is set as NO , Hence skipping this testcase");
        }
        driver = openBrowser(hMap.get("Browser"));
        Thread.sleep(3000);
        HomePage homePage= new HomePage(driver);
        homePage.clickOnMyAccountDropMenu();

        Thread.sleep(3000);
        driver.findElement(By.linkText("Login")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("input-email")).sendKeys(hMap.get("Username"));
        Thread.sleep(3000);
        driver.findElement(By.id("input-password")).sendKeys(hMap.get("Password"));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@value='Login']")).click();
        Thread.sleep(3000);
    }
    @AfterMethod
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @DataProvider
    public Object[][] dataSupplier(){
        Object[][] data = null;
        try {
            excelReader = new MyXLSReader("src\\test\\resources\\TutorialsNinja.xlsx");
            data = DataUtil.getTestData(excelReader, "LoginTest", "Data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
