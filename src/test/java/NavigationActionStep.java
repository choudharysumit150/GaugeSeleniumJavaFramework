
import com.thoughtworks.gauge.Step;
import hf.common.action.NavigationAction;
import hf.common.utils.PropertiesHelper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Sumit Choudhary on 6/15/2019
 */
public class NavigationActionStep {


    private NavigationAction navigationAction;
    public NavigationActionStep() {
        navigationAction = new NavigationAction();

    }

    private static final Logger logger = LoggerFactory.getLogger(NavigationActionStep.class);

    @Step("User Navigate to application home")
    public void userNavigateToApplicationHome() throws Exception {
        logger.info("Launching the application...");
        boolean flag = false;
        try {
            //String url = System.getenv("APP_URL");
            String url = PropertiesHelper.getPropertyValue("app_url");
            if (!StringUtils.isEmpty(url)) {
                flag = navigationAction.navigateToPage(url);
            } else {
                flag = false;
            }
            Assert.assertTrue("Unable to navigate to URL: " + url, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Navigation to url failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in navigating to url", ex);
        }
    }
}
