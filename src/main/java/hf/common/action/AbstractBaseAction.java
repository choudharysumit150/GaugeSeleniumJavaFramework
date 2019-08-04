package hf.common.action;


import hf.common.context.IWebPageContext;
import hf.common.context.WebPageContext;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by Sumit Choudhary on 6/16/2019.
 */


public class AbstractBaseAction {

    protected IWebPageContext context;
    protected WebDriverWait wait;

    public AbstractBaseAction() {
        this.context = WebPageContext.getInstance();
        this.wait = this.context.getWait();
    }
}
