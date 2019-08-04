package hf.common.action;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Sumit Choudhary on 6/15/2019.
 */


public class VerifyAction extends AbstractBaseAction {
    private static final Logger logger = LoggerFactory.getLogger(VerifyAction.class);
    DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();

    public boolean verifyObjectPresent(String name)
    {
        boolean flag = false;

        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            logger.info("Object found: " + name);

            flag=true;

        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + name);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding object " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }

    }
    public boolean verifyTextBoxValue(String name, String expectedValue) {
        boolean flag = false;
        String actualValue = null;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            logger.info("Object found: " + name);
            actualValue = element.getAttribute("value");

            if (actualValue.equalsIgnoreCase(expectedValue)){
                logger.info("Expected text found");
                flag = true;
            }else {
                logger.info("Expected text not found on the page");
                logger.info("Actual Value: " + actualValue);
                flag = false;
            }
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + name);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding object " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
    public boolean verifyTextDisplayingAction(String name, String expectedValue) {
        boolean flag = false;
        String actualValue = null;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            logger.info("Object found: " + name);
            actualValue = element.getText().trim();

            if (actualValue.equalsIgnoreCase(expectedValue)){
                logger.info("Expected text found");
                flag = true;
            }else {
                logger.info("Expected text not found on the page");
                logger.info("Actual Value: " + actualValue);
                flag = false;
            }
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + name);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding object " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
    public boolean verifyPageAndSetContext(String pageName) {
        boolean flag = false;
        try {
            logger.info("Waiting of page to load...");
            context.waitForCurrentPageLoad();
            logger.info("Setting the page context as " + pageName);
            context.setContextCurrentPage(pageName);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(pageName)));
            logger.info("Page found: " + pageName);
            flag = true;
        }catch (TimeoutException e){
            logger.error("TimeoutException - Page not found: " + pageName);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding Page " + pageName);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean getTextAndCompare(String obj1, String obj2)
    {
        boolean flag=false;
        String value1=null;
        String value2=null;
        try {
            WebElement element1 = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(obj1)));
            WebElement element2 = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(obj2)));
            logger.info("Object found: " + obj1);
            logger.info("Object found: " + obj1);
            value1 = element1.getText().trim();
            value2 = element2.getText().trim();

            if (value1.equalsIgnoreCase(value2)){
                logger.info("Value Comparison is true ");
                flag = true;
            }else {
                logger.info("Value Comparison failed for " + obj1 +" and "+obj2);
                logger.info("Actual Value of  " + obj1 + " is : "+ value1);
                logger.info("Actual Value of  " + obj2 + " is : "+ value2);
                flag = false;
            }
        }catch (TimeoutException e){
            logger.error("TimeoutException - Object not found: " + obj1);
            logger.error("TimeoutException - Object not found: " + obj2);
            e.printStackTrace();
        }catch (Exception ex){
            logger.error("Error in finding object " + obj1);
            logger.error("Error in finding object " + obj2);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }

}
