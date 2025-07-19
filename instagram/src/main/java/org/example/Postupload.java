package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class Postupload {
    public static void main(String[] args) {

        // Step 01: Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "Your driver path");

        // Step 02: Maximize browser
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // Step 03: Open Instagram
            driver.get("https://www.instagram.com/");
            System.out.println(" Opening Instagram login page...");

            // Step 04: Login
            driver.findElement(By.name("username")).sendKeys("sandu.vihanga22");
            driver.findElement(By.name("password")).sendKeys("bawantha2001" + Keys.ENTER);
            Thread.sleep(5000);
            takeScreenshot(driver, "1_login");

            // Step 05: Click "New Post"
            driver.findElement(By.cssSelector("svg[aria-label='New post']")).click();
            Thread.sleep(2000);
            takeScreenshot(driver, "2_new_post");

            // Step 06: Select from computer
            WebElement selectBtn = driver.findElement(By.xpath("//button[text()='Select from computer']"));
            selectBtn.click();
            Thread.sleep(2000);

            // Step 07: Upload image
            String imagePath = "C:\\Users\\Acer\\Desktop\\instagram\\src\\image\\pet1.jpg";
            StringSelection selection = new StringSelection(imagePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

            Robot robot = new Robot();
            robot.delay(2000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(8000);
            takeScreenshot(driver, "3_image_uploaded");

            // Step 08: Click "Next" twice
            WebElement nextBtn = driver.findElement(By.xpath("//div[text()='Next']"));
            nextBtn.click();
            Thread.sleep(3000);
            takeScreenshot(driver, "4_next1");

            WebElement nextBtn2 = driver.findElement(By.xpath("//div[text()='Next']"));
            nextBtn2.click();
            Thread.sleep(3000);
            takeScreenshot(driver, "5_next2");

            // Step 09: Add caption
            WebElement captionBox = driver.findElement(By.xpath("//div[@aria-label='Write a caption...']"));
            captionBox.click();
            captionBox.sendKeys("This is my Selenium-automated Instagram post! #selenium #java #automation");
            Thread.sleep(3000);
            takeScreenshot(driver, "6_caption");

            // Step 10: Share
            WebElement shareBtn = driver.findElement(By.xpath("//div[@role='button' and text()='Share']"));
            shareBtn.click();
            Thread.sleep(10000);
            takeScreenshot(driver, "7_post_shared");

            // Step 11: Go to profile
            driver.get("https://www.instagram.com/sandu.vihanga22/");
            System.out.println("Navigated to profile.");
            Thread.sleep(5000);
            takeScreenshot(driver, "8_profile");

            // Step 12: Open latest post
            WebElement uploadedPost = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[contains(@href, '/sandu.vihanga22/p/')]")
            ));
            uploadedPost.click();
            System.out.println("Opened uploaded post.");
            Thread.sleep(4000);
            takeScreenshot(driver, "9_post_opened");

            

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Closing browser.");
            driver.quit();
        }
    }

    // Screenshot method
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File("C:\\Users\\Acer\\Desktop\\instagram\\src\\screenshots" + fileName + ".png");
            org.apache.commons.io.FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved: " + dest.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + fileName);
            e.printStackTrace();
        }
    }
}
