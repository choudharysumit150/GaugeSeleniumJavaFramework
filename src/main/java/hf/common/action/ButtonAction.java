package hf.common.action;

import hf.common.constants.Constants;
import hf.common.utils.Highlight;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Sumit Choudhary on 6/16/2019.
 */

public class ButtonAction extends AbstractBaseAction implements IClickAction {
    private static final Logger logger = LoggerFactory.getLogger(ButtonAction.class);
    @Override
    public boolean click(String name) {
        WebElement element;
        Long startTime = System.currentTimeMillis();
        boolean flag = false;
        try {
            while ((System.currentTimeMillis() - startTime) < Constants.STALE_ELEMENT_WAIT_TIMEOUT * 1000L) {
                try {
                    element = wait.until(ExpectedConditions.elementToBeClickable(context.getElementLocator(name)));
                    if (element.isEnabled()) {
                        logger.info("Button " + name + " found");
                        Highlight.elementHighlight(context.getRealDriver(), element);
                        element.click();
                        logger.info("Button " + name + " clicked");
                        flag = true;
                        break;
                    } else {
                        element = null;
                        logger.debug("Waiting for the button to load...");
                        Thread.sleep(1000);
                    }
                } catch (StaleElementReferenceException e) {
                    logger.debug("Stale Exception: Trying to recover it...");
                } catch (TimeoutException e) {
                    logger.debug("Timeout Exception: Unable to find object: " + name);
                }
            }
        }catch (Exception ex) {
            logger.error("Error in clicking the object: " + name);
            logger.debug(ex.getMessage());
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
}
