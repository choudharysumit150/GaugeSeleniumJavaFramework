package hf.common.action;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 06/15/2019.
 */

public class SelectAction extends AbstractBaseAction implements IInputAction {
    private static final Logger logger = LoggerFactory.getLogger(SelectAction.class);

    @Override
    public boolean setInput(String name, String value) {
        boolean flag = false;
        try{
            logger.info("Select dropdown " + name + " as " + value);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));
            Select dropdown = new Select(element);

                logger.info("Selecting by text");
                dropdown.selectByVisibleText(value);

            flag = true;
            logger.debug("Successfully able to select value: " + value);
        }catch (TimeoutException ex){
            logger.error("TimeoutException -  Unable to find object: " + name);
        }
        catch (Exception ex){
            logger.error("Error in selecting a value from dropdown " + name);
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }



    @Override
    public boolean setInputAutoPopulate(String name, String value) {
        return false;
    }

    @Override
    public boolean performAction(String name, String action) {
        return false;
    }
}
