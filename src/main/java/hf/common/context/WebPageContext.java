package hf.common.context;

import hf.common.constants.Constants;
import hf.common.repository.DataNotFoundInRepoExecption;
import hf.common.repository.PageElement;
import hf.common.repository.RepositoryContext;
import hf.common.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sumit Choudhary on 6/14/2019.
 */
public class WebPageContext implements IWebPageContext {
    private static final Logger logger = LoggerFactory.getLogger(WebPageContext.class);

    private static WebDriverWait wait;
    private static WebDriverWait shortWait;
    private static WebPageContext webPageContext;
    private WebPageContext(){}
    public static WebPageContext getInstance(){
        if (webPageContext==null){
            logger.debug("Creating instance of web context");
            webPageContext = new WebPageContext();
        }
        return webPageContext;
    }

    private RepositoryContext repository = RepositoryContext.getInstance();
    private ExpectedCondition<Boolean> document_readyState_toBeComplete =
            webDriver -> Boolean.valueOf(((JavascriptExecutor)Driver.webDriver).executeScript("return document.readyState", new Object[0]).toString().equalsIgnoreCase("complete"));

    @Override
    public By getElementLocator(String elementName) {
        By locElement = null;
        try {
            PageElement element = this.repository.getElement(elementName);
            locElement = this.getLocator(element);
            logger.debug("Locator found for object " + elementName);
        } catch (DataNotFoundInRepoExecption dataNotFoundInRepoExecption) {
            logger.info(elementName + " not found in repository");
            dataNotFoundInRepoExecption.printStackTrace();
        }finally {
            return locElement;
        }
    }

    @Override
    public WebDriver getRealDriver() {
        //return DriverFactory.getWebDriver();
        return Driver.webDriver;
    }

    @Override
    public WebDriverWait getWait() {
        if (wait==null){
            logger.debug("Initializing WebDriverWait");
            wait = new WebDriverWait(Driver.webDriver, Constants.WAIT_TIMEOUT);
        }
        return wait;
    }

    @Override
    public WebDriverWait getShortWait() {
        if (shortWait==null){
            logger.debug("Initializing shortWaitDriver");
            shortWait = new WebDriverWait(Driver.webDriver, Constants.SHORT_WAIT_TIMEOUT);
        }
        return shortWait;
    }

    @Override
    public void waitForCurrentPageLoad()  {
        logger.debug("Waiting to load page");
        wait.until(this.document_readyState_toBeComplete);
    }

    @Override
    public void setContextCurrentPage(String pageName) {
        logger.debug("Set context as " + pageName);
        this.repository.setContextCurrentPage(pageName);
    }

    private By getLocator(PageElement element) throws DataNotFoundInRepoExecption {
        logger.debug("Locating element: " + element);
        if (element.getLocatorType().equalsIgnoreCase("name")){return By.name(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("id")){return By.id(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("xpath")){return By.xpath(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("className")){return By.className(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("partialLinkText")){return By.partialLinkText(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("linkText")){return By.linkText(element.getLocator());}
        else if (element.getLocatorType().equalsIgnoreCase("css")){return By.cssSelector(element.getLocator());}
        else {throw new DataNotFoundInRepoExecption("Locator not found for webelement " + element);}
    }
}
