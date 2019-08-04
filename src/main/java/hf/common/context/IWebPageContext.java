package hf.common.context;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;



/**
 * Created by Sumit Choudhary on 6/14/2019.
 */
public interface IWebPageContext {
    By getElementLocator(String var1);
    WebDriver getRealDriver();
    WebDriverWait getWait();
    WebDriverWait getShortWait();
    void waitForCurrentPageLoad() throws Exception;
    void setContextCurrentPage(String var1);
}
