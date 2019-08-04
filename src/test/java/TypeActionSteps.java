import com.thoughtworks.gauge.Step;
import hf.common.action.TypeAction;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 6/14/2019
 */
public class TypeActionSteps {
    private static final Logger logger = LoggerFactory.getLogger(TypeActionSteps.class);
    private TypeAction typeAction;

    public TypeActionSteps() {
        typeAction = new TypeAction();
    }

    @Step("User enter <objName> as <value>")
    public void setInput(String objName, String value) throws Exception {
        boolean flag = false;
        try {
            logger.info("Entering text " + value + " in textbox " + objName);
            flag = typeAction.setInputAutoPopulate(objName, value);
            Assert.assertTrue("Failed to enter value: " + value + " in field: " + objName, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Text enter operation failed", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in entering the text for object: " + objName, ex);
        }
    }

    @Step("User perform <clean> action on <FromLocation> textbox")
    public void clearTextBox(String action, String name) throws Exception {
        boolean flag = false;

        try {
            flag = typeAction.performAction(name, action);
            Assert.assertTrue("Failed to perform : " + action + " in field: " + name, flag);

        } catch (AssertionError assertionError) {
            throw new Exception("Failed to perform " + action, assertionError);
        } catch (Exception ex) {
            throw new Exception("Failed to perform " + action + name, ex);
        }

    }
}
