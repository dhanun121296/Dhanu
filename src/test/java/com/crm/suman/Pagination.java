package com.crm.suman;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pagination
{
    public static void main(String[] args)
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://testautomationpractice.blogspot.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        JavascriptExecutor js= (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//h2[.='Pagination Web Table']"));
        js.executeScript("arguments[0].scrollIntoView(true);",element);

        String productName="Soundbar";
        for(int i=1;;i++)
        {
            boolean flag = false;
            List<WebElement> productNames = driver.findElements(By.xpath("//table[@id='productTable']/tbody/tr/td[2]"));
            for(WebElement pNames:productNames)
            {
                if (productName.equalsIgnoreCase(pNames.getText()))
                {
                    driver.findElement(By.xpath("//tr/td[.='"+productName+"']/following-sibling::td[2]/input")).click();
                    String price = driver.findElement(By.xpath("//table[@id='productTable']/tbody/tr/td[contains(.,'$')]")).getText();
                    System.out.println("price"+"="+price);
                    flag=true;
                    break;
                }
            }
            if (flag)
            {
                break;
            }
            try
            {
                driver.findElement(By.xpath("//ul[@id='pagination']/li["+(i)+"]")).click();
            } catch (Exception e)
            {
                System.out.println("No record found"+e);
                break;
            }
        }
    }
}
