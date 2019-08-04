package hf.common.action;

import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 6/15/2019.
 */

public class TypeAction extends AbstractBaseAction implements IInputAction {
    private static final Logger logger = LoggerFactory.getLogger(TypeAction.class);
    DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();

    @Override
    public boolean setInput(String name, String value) {
        boolean flag = false;
        try{
            logger.info("Enter text " + value + " in field " + name);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            element.clear();
            element.sendKeys(value);
            flag = true;
            logger.debug("Successfully able to enter value: " + value);
        }catch (TimeoutException ex){
            logger.error("TimeoutException -  Unable to find textbox : " + name);
            ex.printStackTrace();
        }
        catch (Exception ex){
            logger.error("Error in entering a value in textbox : " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }

    @Override
    public boolean setInputAutoPopulate(String name, String value) {
        boolean flag = false;
        try{
            logger.info("Enter text " + value + " in field " + name);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            if(name.equalsIgnoreCase("DateOfTravel"))
            {
                element.click();
                Thread.sleep(500);
                element.click();

                Thread.sleep(500);
                String value1 =element.getAttribute("value");
                for(int i=0 ;i<value1.length();i++)
                {
                    element.sendKeys(Keys.BACK_SPACE);
                }
                element.sendKeys(value);
                Thread.sleep(500);
               // element.sendKeys(Keys.ENTER);
                flag=true;
            }else {
                element.click();
                Thread.sleep(500);
                element.clear();
                Thread.sleep(500);
                element.sendKeys(value);
                Thread.sleep(500);
                element.sendKeys(Keys.ENTER);
                flag = true;
            }
            logger.debug("Successfully able to enter value: " + value);
        }catch (TimeoutException ex){
            logger.error("TimeoutException -  Unable to find textbox: " + name);
            ex.printStackTrace();
        }
        catch (Exception ex){
            logger.error("Error in entering a value in textbox: " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
}

    @Override
    public boolean performAction(String name, String action) {
        logger.info("Performing " + action + " on " + name);
        boolean flag = false;
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            if (element.isEnabled()) {
                logger.info("Element found : " + name);
                element.clear();
                flag = true;
            } else {
                logger.error("Element is not enabled :" + name);
            }
        } catch (TimeoutException ex)
        {
            logger.error("Error in performing the " +action +" on " + name);
            ex.printStackTrace();
        }
        return flag;
    }
}
