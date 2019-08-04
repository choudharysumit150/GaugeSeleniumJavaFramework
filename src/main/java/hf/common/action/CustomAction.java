package hf.common.action;

import hf.common.dataclasses.SearchResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CustomAction extends AbstractBaseAction {

    private static final Logger logger = LoggerFactory.getLogger(CustomAction.class);

    public void checkForBrokenHyperLink() {

        List<WebElement> hyperlinks = context.getRealDriver().findElements(By.tagName("a"));

        logger.info("Total links on the page : "+ hyperlinks.size());


        for(int i=0; i<hyperlinks.size(); i++) {
            WebElement element = hyperlinks.get(i);
            String url=element.getAttribute("href");
            checkIfLinkIsBroken(url);
        }



    }

    public static void checkIfLinkIsBroken(String urlLink) {

        logger.info("Checking the link :" +urlLink);
        try {

            URL link = new URL(urlLink);

            HttpURLConnection httpConn =(HttpURLConnection)link.openConnection();

            httpConn.setConnectTimeout(2000);

            httpConn.connect();

            if(httpConn.getResponseCode()== 200) {
               logger.info(" Link is valid : "+ urlLink);
            }
            if(httpConn.getResponseCode()!= 200) {
               logger.info("Link is down : " +urlLink);
            }
        }

        catch (MalformedURLException e) {
           logger.error("URL is not formatted correctly : " +urlLink + e.getLocalizedMessage());
        } catch (IOException e) {
            logger.error("Not able to connect to  : " +urlLink + e.getLocalizedMessage());
        }
    }

    public List<SearchResult> getActualData(String name) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(context.getElementLocator(name)));

        List<SearchResult> listSearchResults = new ArrayList<SearchResult>();
        SearchResult searchResult = null;

        if (element.isDisplayed()) {
            List<WebElement> lists = context.getRealDriver().findElements(By.xpath("//ul[@class='_h9wfdq']//li"));
            int recordsCount = lists.size();
            String departureTime = null;
            String arrivalTime = null;
            String standardFare = null;
            String travelTime = null;
            String interChange = null;
            String firstClassFare = null;

            for (int i = 1; i <= recordsCount; i++) {
                try {
                    searchResult = new SearchResult();
                    //Getting the values from WebPage;
                    departureTime = context.getRealDriver().findElement(By.xpath("//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='train-results-departure-time']")).getText();
                    arrivalTime = context.getRealDriver().findElement(By.xpath("//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='train-results-arrival-time']")).getText();
                    List<WebElement> bothFare = context.getRealDriver().findElements(By.xpath("//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='alternative-price']"));

                    if (bothFare.size() > 1) {
                        standardFare = context.getRealDriver().findElement(By.xpath("(//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='alternative-price'])[1]")).getText();
                        firstClassFare = context.getRealDriver().findElement(By.xpath("(//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='alternative-price'])[2]")).getText();
                    } else {
                        standardFare = context.getRealDriver().findElement(By.xpath("//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='alternative-price']")).getText();
                    }
                    travelTime = context.getRealDriver().findElement(By.xpath("//ul[@class='_h9wfdq']//li[" + i + "]//div[@data-test='changes-link']//div[@class='_16g9b5x']")).getText();
                    interChange = travelTime.split(",")[1].trim();
                    travelTime = travelTime.split(",")[0].trim();
                    //context.getRealDriver().findElement(By.xpath("//ul[@class='_h9wfdq']//li["+i+"]//div[@data-test='changes-link']//div[@class='_1ic8min']//button//span")).getText();

                    //Storing data into Results object
                    searchResult.setTrainDepartureTime(departureTime);
                    searchResult.setTrainArrivalTime(arrivalTime);
                    searchResult.setStandardFare(standardFare);
                    searchResult.setTravelTime(travelTime);
                    searchResult.setInterchangeCount(interChange);
                    searchResult.setFirstClassFare(firstClassFare);

                    //Adding data to List object of SearchResults
                    listSearchResults.add(searchResult);

                } catch (Exception ex) {
                    logger.error("Issue in reading the Searching results " + ex.getMessage());
                }

            }
        }
        return listSearchResults;
    }

}

