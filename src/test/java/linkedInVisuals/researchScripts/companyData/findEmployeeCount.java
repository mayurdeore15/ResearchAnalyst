package linkedInVisuals.researchScripts.companyData;

import Utilities.DataUtil;
import Utilities.MyXLSReader;
import linkedInVisuals.pageObjects.LinkedInHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.*;
import setUp.TestBase;

import java.io.IOException;
import java.util.HashMap;

public class findEmployeeCount extends TestBase {
    WebDriver driver;
    MyXLSReader excelReader;


//    public findEmployeeCount(WebDriver driver){
//        this.driver=driver;
//    }

    @BeforeTest
    public void Login() throws IOException {
        driver = openBrowser();
        LinkedInHomePage linkedInHomePage= new LinkedInHomePage(driver);
        linkedInHomePage.Login("mayur4945@gmail.com","DW@mjd99");
    }
    @Test(dataProvider = "dataSupplier")
    public void findEmployee(HashMap<String,String> hMap) throws Exception {
        if(!DataUtil.isRunnable(excelReader,"findEmployee","Testcases") || hMap.get("Runmode").equals("N")){
            throw new SkipException("Run Mode is set as NO , Hence skipping this testcase");
        }
        LinkedInHomePage linkedInHomePage= new LinkedInHomePage(driver);
//        linkedInHomePage.Login("mayur4945@gmail.com","DW@mjd99");
        linkedInHomePage.SearchCompany(hMap.get("CompanyName"));
        linkedInHomePage.NavigateToCompanySize();

        String GetLinkedInEmployeeCount = linkedInHomePage.GetLinkedInEmployeeCount();
        int GetEmployeesOnLinkedIn = linkedInHomePage.GetEmployeesOnLinkedIn();

        DataUtil.setStringTestData(excelReader, "findEmployee", "Data", 3,3,hMap.get("CompanyName"),GetLinkedInEmployeeCount);
        DataUtil.setIntTestData(excelReader, "findEmployee", "Data", 3,4,hMap.get("CompanyName"),GetEmployeesOnLinkedIn);
        System.out.println(GetLinkedInEmployeeCount);
        System.out.println(GetEmployeesOnLinkedIn);
    }

    @AfterTest
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }












    @DataProvider
    public Object[][] dataSupplier(){
        Object[][] data = null;
        try {
            excelReader = new MyXLSReader("src\\test\\resources\\linkedInVisuals\\Linkedin Visuals_Omkar.xlsx");
            data = DataUtil.getTestData(excelReader, "findEmployee", "Data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
