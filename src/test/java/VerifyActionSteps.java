import com.thoughtworks.gauge.Step;
import hf.common.action.VerifyAction;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 6/15/2019
 */
public class VerifyActionSteps {

    private static final Logger logger = LoggerFactory.getLogger(VerifyActionSteps.class);
    private VerifyAction verifyAction;


    public VerifyActionSteps() {
        this.verifyAction = new VerifyAction();
    }

    @Step("User on <pageName> page")
    public void verifyOnPageActionSteps(String pageName) throws Exception {
        boolean flag = false;
        try {
            System.out.println(System.getProperty("browser"));
            logger.info("Verifying the " + pageName);
            flag = verifyAction.verifyPageAndSetContext(pageName);
            Assert.assertTrue("Page not found: " + pageName, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Page not found: " + pageName, assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding Page: " + pageName, ex);
        }
    }


    @Step("Verify that <SearchResultList> value is <FromCity>")
    public void verifyTheValue(String name, String value) throws Exception {
        boolean flag = false;
        try {
            logger.info("Verifying object " + name + " has value " + value);
            flag = verifyAction.verifyTextDisplayingAction(name, value);
            Assert.assertTrue("Unable to find the text: " + value, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Text not available", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding text: " + value, ex);
        }
    }


    @Step("Verify that <CheapestPrice> value is equals to <CheapestPriceInList>")
    public void compareValues(String obj1, String obj2) throws Exception {
        boolean flag = false;
        try {
            logger.info("Comparing the values of  " + obj1 + " and" + obj2);
            flag = verifyAction.getTextAndCompare(obj1, obj2);
            Assert.assertTrue("Values not matched :" + obj1 + " amd " + obj2, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Text not available", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in Comparing text: " + obj1, ex);
        }
    }


    @Step("Verify that <FromStationMandatoryError> should not be displayed")
    public void verifyObjectNotPresent(String name) throws Exception {
        boolean flag = false;
        try {
            logger.info("Verifying object present :" + name);
            flag = verifyAction.verifyObjectPresent(name);
            Assert.assertFalse("Object found :" + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Object still visible ", assertionError);
        } catch (Exception ex) {
            throw new Exception("Object still visible : " + name, ex);
        }
    }

    @Step("Verify that <SwapLocation> should be displayed")
    public void verifyObjectPresent(String name) throws Exception {
        boolean flag = false;
        try {
            logger.info("Verifying object present :" + name);
            flag = verifyAction.verifyObjectPresent(name);
            Assert.assertTrue("Object found :" + name, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Object still visible ", assertionError);
        } catch (Exception ex) {
            throw new Exception("Object still visible : " + name, ex);
        }
    }

    @Step("Verify that <FromLocation> textbox value should be <Namborn>")
    public void verifyValueOfTextbox(String name, String value) throws Exception {
        boolean flag = false;
        try {
            logger.info("Verifying object " + name + " has value " + value);
            flag = verifyAction.verifyTextBoxValue(name, value);
            Assert.assertTrue("Value not matched : " + value, flag);
        } catch (AssertionError assertionError) {
            throw new Exception("Value not matched", assertionError);
        } catch (Exception ex) {
            throw new Exception("Error in finding text: " + value, ex);
        }
    }


}
