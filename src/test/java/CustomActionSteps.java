import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import hf.common.action.CustomAction;
import hf.common.action.SelectAction;
import hf.common.action.VerifyAction;
import hf.common.dataclasses.SearchResult;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomActionSteps {

    private static final Logger logger = LoggerFactory.getLogger(ButtonActionSteps.class);
    private CustomAction customAction;
    private SelectAction selectAction;
    private VerifyAction verifyAction;

    public CustomActionSteps() {

        this.customAction = new CustomAction();
        this.selectAction = new SelectAction();
        this.verifyAction = new VerifyAction();
    }

    @Step("Verify the <SearchResultList> with <ResultsFile>")
    public void VerifySearchResults(String name, String resultFile) {

        logger.info("Storing data in the lists");
        //Storing the results in the list
        List<SearchResult> expectedResults = getExpectedResults(resultFile);
        List<SearchResult> actualData = customAction.getActualData(name);
        logger.info("Sorting the  data in the lists before comparison");
        //Sorting the collection before comparing
        Collections.sort(expectedResults);
        Collections.sort(actualData);

        boolean areEqual = compareSearchResult(expectedResults, actualData);

        try {
            Assert.assertTrue("Data Not Matched", areEqual);
        } catch (AssertionError error) {
            logger.error("Search Result not matched with expected result " + error.getMessage());
        }

    }

    public boolean compareSearchResult(List<SearchResult> expected, List<SearchResult> actual) {

        logger.info("Starting comparison of two list : " + expected.size());
        boolean isDataMatched = false;
        if (expected.size() == actual.size()) {
            for (SearchResult exp : expected) {
                for (SearchResult act : actual) {
                    if (exp.getTrainDepartureTime().equalsIgnoreCase(act.getTrainDepartureTime()) &&
                            exp.getTrainArrivalTime().equalsIgnoreCase(act.getTrainArrivalTime()) &&
                            exp.getStandardFare().equalsIgnoreCase(act.getStandardFare()) &&
                            exp.getTravelTime().equalsIgnoreCase(act.getTravelTime()) &&
                            exp.getInterchangeCount().equalsIgnoreCase(act.getInterchangeCount()))
                        isDataMatched = true;
                    else
                        isDataMatched = false;
                    return isDataMatched;
                }
            }
        }
        logger.info("Data matched in the lists : " + isDataMatched);
        return isDataMatched;
    }

    public List<SearchResult> getExpectedResults(String resultFile) {

        logger.info("Stating getting expected results for : " + resultFile);
        CSVReader csvReader = null;
        CsvToBean csvToBean = null;
        List<SearchResult> testData = null;
        String FILE_PATH = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/test/resources/TestData/" + resultFile;
        Map<String, String> mapping = new HashMap<String, String>();
        mapping.put("DepartureTime", "trainDepartureTime");
        mapping.put("ArrivalTime", "trainArrivalTime");
        mapping.put("StandardFare", "standardFare");
        mapping.put("FirstClassFare", "firstClassFare");
        mapping.put("TravelTime", "travelTime");
        mapping.put("InterChange", "interchangeCount");
        HeaderColumnNameTranslateMappingStrategy<SearchResult> strategy = new HeaderColumnNameTranslateMappingStrategy<SearchResult>();
        strategy.setType(SearchResult.class);
        strategy.setColumnMapping(mapping);

        try {
            csvReader = new CSVReader(new FileReader(FILE_PATH));
        } catch (FileNotFoundException e) {

            logger.error("Test Data file not found : " + resultFile + e.getMessage());
        }
        csvToBean = new CsvToBean();
        testData = csvToBean.parse(strategy, csvReader);
        return testData;
    }

    @Step("Validate the valid combination of <Adults> and <Childrens> <table>")
    public void verifyingData(String name1, String name2, Table table) throws Exception {
        boolean flag = false;
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        String adultCount = null;
        String childCount = null;
        String message = null;
        for (TableRow row : rows) {

            adultCount = row.getCell((columnNames.get(0)));
            childCount = row.getCell((columnNames.get(1)));
            message = row.getCell((columnNames.get(2)));

            selectAction.setInput(name1, adultCount);
            selectAction.setInput(name2, childCount);
            flag = verifyAction.verifyTextDisplayingAction("PassengerSummaryMessage", message);
            if (!flag)
                break;

        }
        try {
            Assert.assertTrue("All combinations not matched", flag);
        } catch (AssertionError assertionError) {
            throw new Exception("All combinations are not matched ", assertionError);
        } catch (Exception ex) {
            throw new Exception("All combinations are not matched ", ex);
        }
    }


    @Step("Check if any hyperlink is broken")
    public void checkForBrokenHyperlinks() {

        customAction.checkForBrokenHyperLink();


    }
}

