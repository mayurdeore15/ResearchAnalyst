package naukariVisuals.researchScripts.Testing;

import Utilities.DataUtil;
import Utilities.MyXLSReader;
import naukariVisuals.pageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import setUp.TestBase;

import java.util.HashMap;

public class SkillsTest extends TestBase {
    WebDriver driver;
    MyXLSReader excelReader;
    @Test(dataProvider = "dataSupplier")
    public void Skills(HashMap<String,String> hMap) throws Exception {

        if(!DataUtil.isRunnable(excelReader,"Skills","Testcases") || hMap.get("Runmode").equals("N")){
            throw new SkipException("Run Mode is set as NO , Hence skipping this testcase");
        }
        driver = openBrowser(hMap.get("Browser"));
        HomePage homePage= new HomePage(driver);
        homePage.click_SearchJobHere();
        homePage.click_EnterKeyWord();
        homePage.sendKeys_KeyWord(hMap.get("Designation"));
        homePage.click_Search();
        int TotalJob = homePage.getJobCount();
        DataUtil.setTestData(excelReader, "Skills", "Data", 3,5,hMap.get("Designation"),TotalJob);
        System.out.println(TotalJob);
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
            excelReader = new MyXLSReader("src\\test\\resources\\naukariVisualsData\\NaukariVisualsData.xlsx");
            data = DataUtil.getTestData(excelReader, "Skills", "Data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}