package com.example.demo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class TodoPageTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get("http://localhost:8080");

    }

    @Test
    public void testHome() throws InterruptedException {
        
        assertEquals("Home", driver.getTitle());
    }
    
    @Test
    public void testCreatePage() throws InterruptedException {
        WebElement createBut = driver.findElement(By.id("createButton"));
    	createBut.click();
        assertEquals("Create", driver.getTitle());
    }
    
    @Test
    public void testUpdatePage() throws InterruptedException {
        WebElement updateBut = driver.findElement(By.id("updateButton"));
    	updateBut.click();
        assertEquals("Update", driver.getTitle());
    }
    
    @Test
    public void testCreateTodo() throws InterruptedException {
        WebElement createBut = driver.findElement(By.id("createButton"));
        createBut.click();
        WebElement todoName = driver.findElement(By.id("floatingName"));
        String tName = "Bake cake";
        todoName.sendKeys(tName);
        WebElement todoDes = driver.findElement(By.id("floatingDescription"));
        String tDes = "Make by tomorrow";
        todoDes.sendKeys(tDes);
        WebElement subBut = driver.findElement(By.id("submitButton"));
        subBut.click();
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void testCreateTask() throws InterruptedException {
        WebElement createBut = driver.findElement(By.id("createButton"));
        createBut.click();
        WebElement todoName = driver.findElement(By.id("floatingNameT"));
        String tName = "Egg";
        todoName.sendKeys(tName);
        WebElement todoDes = driver.findElement(By.id("floatingDifficulty"));
        String tDes = "Easy";
        todoDes.sendKeys(tDes);
        WebElement todoID = driver.findElement(By.id("floatingTodo"));
        String tID = "1";
        todoID.sendKeys(tID);
        WebElement subBut = driver.findElement(By.id("submitButtonT"));
        subBut.click();
        driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void testUpdateTodo() throws InterruptedException {
        WebElement createBut = driver.findElement(By.id("updateButton"));
        createBut.click();
        WebElement todoID = driver.findElement(By.id("floatingID"));
        String tID = "1";
        todoID.sendKeys(tID);
        WebElement todoName = driver.findElement(By.id("floatingName"));
        String tName = "Build a house";
        todoName.sendKeys(tName);
        WebElement todoDes = driver.findElement(By.id("floatingDescription"));
        String tDes = "Big House";
        todoDes.sendKeys(tDes);
        WebElement subBut = driver.findElement(By.id("submitButtonT"));
        subBut.click();
        driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void testUpdateTask() throws InterruptedException {
        WebElement createBut = driver.findElement(By.id("updateButton"));
        createBut.click();
        WebElement todoID = driver.findElement(By.id("floatingIDTask"));
        String tID = "1";
        todoID.sendKeys(tID);
        WebElement todoName = driver.findElement(By.id("floatingNameTask"));
        String tName = "Bricks";
        todoName.sendKeys(tName);
        WebElement todoDes = driver.findElement(By.id("floatingDifficulty"));
        String tDes = "Hard";
        todoDes.sendKeys(tDes);
        WebElement subBut = driver.findElement(By.id("submitButtonT"));
        subBut.click();
        driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void testSearch() throws InterruptedException {
        WebElement searchID = driver.findElement(By.id("floatingID"));
        String tID = "1";
        searchID.sendKeys(tID);
        WebElement subBut = driver.findElement(By.id("submit"));
        subBut.click();
        //driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void deleteID() throws InterruptedException {
        WebElement searchID = driver.findElement(By.id("floatingDelID"));
        String tID = "1";
        searchID.sendKeys(tID);
        WebElement subBut = driver.findElement(By.id("submitID"));
        subBut.click();
        //driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void deleteTask() throws InterruptedException {
        WebElement searchID = driver.findElement(By.id("floatingDelIDT"));
        String tID = "1";
        searchID.sendKeys(tID);
        WebElement subBut = driver.findElement(By.id("submitTask"));
        subBut.click();
        //driver.switchTo().alert().dismiss();
    }
    
    @Test
    public void testReadAll() throws InterruptedException {
        WebElement subBut = driver.findElement(By.id("readAllButton"));
        subBut.click();
    }
    
    
    @AfterEach
    public void tearDown() {
        driver.close();
    }
}