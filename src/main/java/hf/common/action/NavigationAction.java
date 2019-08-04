package hf.common.action;

import hf.common.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sumit Choudhary on 6/15/2019.
 */

public class NavigationAction extends AbstractBaseAction {
    private static final Logger logger = LoggerFactory.getLogger(NavigationAction.class);
    ButtonAction buttonAction;
    public NavigationAction() {
        this.buttonAction = new ButtonAction();
    }

    //Open URL in browser
    public boolean navigateToPage(String url){
        boolean flag = false;
        try{
            logger.info("Application URL: " + url);
            context.getRealDriver().get(url);
            context.getRealDriver().manage().window().maximize();
            context.getRealDriver().manage().timeouts().implicitlyWait(Constants.NAVIGATION_WAIT_TIMEOUT, TimeUnit.SECONDS);



            flag = true;
        }catch (Exception ex){
            logger.error("Error in navigating to URL: "+ url);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            return flag;
        }
    }
}
