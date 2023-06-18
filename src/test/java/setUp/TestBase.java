package setUp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;
    public WebDriver openBrowser() throws IOException {

        File PropFile = new File("src\\test\\resources\\Properties\\Global.properties");
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(PropFile);
        prop.load(fis);
        String URL = prop.getProperty("URL");
        String browser_properties = prop.getProperty("browser");
        System.out.println(browser_properties);
        String browser_maven = System.getProperty("browser");
        String browser = browser_maven != null? browser_maven : browser_properties;

//        try{
//            FileInputStream fis = new FileInputStream(PropFile);
//            prop.load(fis);
//        }catch (Throwable e){
//            e.printStackTrace();
//        }
        if(browser.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
        }else {
            System.out.println("Please add browser name");
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(URL);
        return driver;
    }
}
