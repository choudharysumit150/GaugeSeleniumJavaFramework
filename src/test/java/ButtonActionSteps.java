import com.thoughtworks.gauge.Step;
import hf.common.action.ButtonAction;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 6/15/2019
 */
public class ButtonActionSteps {

    private ButtonAction buttonAction;
    public ButtonActionSteps() {
        this.buttonAction = new ButtonAction();
    }
    private static final Logger logger = LoggerFactory.getLogger(ButtonActionSteps.class);

    @Step("User click <name> button")
    public void click(String name) throws Exception {
        boolean flag = false;
        try {
            logger.info("Clicking button " + name);
            flag = buttonAction.click(name);
            Assert.assertTrue("Failed to click: " + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Click operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in clicking: " + name, ex);
        }
    }
}
