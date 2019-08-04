Search Result Verification in London Area
=====================
Created by Sumit Choudahary on 15/06/2019

```````````Using external file to make seprate data from test case
```````````Can execute same test multiple time depending on the number of rows in csv


table:src/test/resources/TestData/SearchData.csv

* User Navigate to application home

Verify user able to perform Search and validating the search results
----------------
tags: searchTrainsLondon

* User on "Home" page
* User enter "FromLocation" as <FromCity>
* User enter "DestinationLocation" as <DestinationCity>
* User enter "DateOfTravel" as <TravelDate>
* User select "Hours" as <Hours>
* User select "Minutes" as <Minutes>
* User click "GetTimeTickets" button
* User on "SearchResults" page
* Verify that "FromStation" value is <FromCity>
* Verify that "DestinationStation" value is <DestinationCity>

 Verifying the Cheapest Price in List with showing on Right Side Buy ticket
 
* Verify that "CheapestPrice" value is equals to "CheapestPriceInList"
* Verify the "SearchResultList" with <ResultsFile>




Getting broken links information if any on Search Result page
----------------
tags: homepage

* User on "Home" page
* User enter "FromLocation" as "Berlin"
* User enter "DestinationLocation" as "Namborn"
* User click "GetTimeTickets" button
* Check if any hyperlink is broken










