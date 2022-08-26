package testmaster.selenium.methods;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import testmaster.selenium.driver.Driver;

import java.time.Duration;

public class Methods {

    WebDriver driver;
    FluentWait<WebDriver> fluentWait;
    JavascriptExecutor jsDriver;
    public Methods(){

        this.driver = Driver.driver;
        jsDriver = (JavascriptExecutor) driver;
        fluentWait = setFluentWait(30);
    }

    public FluentWait<WebDriver> setFluentWait(long timeout){

         return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findElement(By by){

        return driver.findElement(by);
    }

    public WebElement findElementWait(By by){

        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void clickElement(By by){

        findElementWait(by).click();
    }

    public void sendKeys(By by, String text){

        findElementWait(by).sendKeys(text);
    }

    public String getText(By by){

        return findElementWait(by).getText();
    }

    public String getAttribute(By by, String attribute){

        return findElementWait(by).getAttribute(attribute);
    }

    public void hoverElement(By by){

        WebElement webElement = findElementWait(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public String getValue(By by){

        WebElement webElement = findElementWait(by);
        return jsDriver.executeScript("return arguments[0].value;", webElement).toString();
    }
    // button[data-testid='login-button']"
}
