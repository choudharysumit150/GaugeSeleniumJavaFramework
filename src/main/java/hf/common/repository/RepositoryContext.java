package hf.common.repository;

/**
 * Created by Sumit Choudhary on 6/16/2019
 */
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Data
public class RepositoryContext {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryContext.class);
    private String contextCurrentPage = "Home";
    private String repoPath;
    private WebRepository webRepository;

    private static RepositoryContext repositoryContext;
    private RepositoryContext(){}
    public static RepositoryContext getInstance(){
        if (repositoryContext==null){
            repositoryContext = new RepositoryContext();
        }
        return repositoryContext;
    }


    public void loadRepository(){
        try {
            if(!StringUtils.isEmpty(repoPath)) {
                logger.debug("Load Repository - Start");
                logger.debug("Repository Path: " + this.repoPath);
                StreamSource io = new StreamSource(new FileInputStream(new File(this.repoPath)));
                JAXBContext jaxbContext = JAXBContext.newInstance(WebPage.class,PageElement.class, WebRepository.class);
                this.webRepository = (WebRepository) jaxbContext.createUnmarshaller().unmarshal(io);
                logger.debug("webRepository: " + this.webRepository);
                logger.debug("Load Repository - End");
            }else {
                logger.info("Object Repository path not available");
            }

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public WebPage getWebPage(String pageName) throws DataNotFoundInRepoExecption {
        Iterator itr = this.webRepository.getWebPages().iterator();
        WebPage page;
        do{
            if(!itr.hasNext()){
                logger.error(pageName + "page not fount in repository xml");
                throw new DataNotFoundInRepoExecption(pageName + "page not fount in repository xml");
            }

            page = (WebPage)itr.next();
        }while(!page.getPageName().equals(pageName));

        return page;
    }

    public PageElement getElement(String pageName, String elmName){
        PageElement element = null;
        try {
            element = this.getWebElement(pageName, elmName);
            logger.debug("Element " + elmName + " found on page " + pageName);
        }catch (DataNotFoundInRepoExecption ex){
            logger.warn(ex.getMessage());
            logger.warn("Searching element " + elmName + " not found on page " + pageName);
        }
        return element;
    }

    public PageElement getElement(String elementName){
        return this.getElement(this.contextCurrentPage, elementName);
    }

    public PageElement getWebElement(String pageName, String elmName) throws DataNotFoundInRepoExecption {
        Iterator itr = this.getWebPage(pageName).getWebPageElement().getWebElements().iterator();
        PageElement element;

        do{
            if(!itr.hasNext()){
                logger.error(elmName + " not found in repository");
                throw new DataNotFoundInRepoExecption(elmName + " not found in repository");
            }
            element = (PageElement)itr.next();
        }while (!element.getElementName().equals(elmName));

        return element;
    }

}