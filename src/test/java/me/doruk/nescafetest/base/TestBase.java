package me.doruk.nescafetest.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public abstract class TestBase {

    public static WebDriver driver;
    public static WebDriverWait wait;
    private static boolean isDriverSet = false;

    public static void setDriver() {
        if (!isDriverSet) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    Paths
                            .get("")
                            .resolve("./drivers/chromedriver.exe")
                            .toAbsolutePath()
                            .toString()
            );

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");

            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));
            isDriverSet = true;
        }
    }

    public static void waitPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static void destroyDriver() {
        if (isDriverSet) {
            driver.quit();
            isDriverSet = false;
        }
    }

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException ignored) {
        }
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000L));
        } catch (InterruptedException ignored) {
        }
    }
}
