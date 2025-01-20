import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public abstract class TestParent {
    static Properties properties;
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    static Yaml yaml;
    static InputStream inputStream;
    static Map<String, Object> yamlData;

    public void loadYaml() {
        yaml = new Yaml();
        try {
            inputStream = new FileInputStream("config.yaml");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        yamlData = yaml.load(inputStream);
    }

    @Attachment(value = "Screenshot")
    public byte[] createAttachment() {
        return page.screenshot();
    }

    @BeforeSuite
    public void startDataPreparation() throws IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
        loadYaml();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)) ;
    }

    @BeforeMethod
    public void startTestCase() throws IOException {
        context = browser.newContext();
        page = context.newPage();
        page.navigate(yamlData.get("SauceDemo_URL").toString());
    }

    @AfterMethod
    public void closeTestCase(ITestResult testResult) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            createAttachment();
        }
        context.close();
    }

    @AfterSuite
    public void addAllureInformation() {
        playwright.close();
        Properties allure = new Properties();
        allure.setProperty("Platform", "Web");
        allure.setProperty("Library", "Playwright");
        allure.setProperty("Code by", "Ahmad Azeri Chandra Bhuana");
        try {
            File file = new File("./allure-results/environment.properties");
            FileOutputStream fileOut = new FileOutputStream(file);
            allure.store(fileOut, "Allure Report Environment");
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}