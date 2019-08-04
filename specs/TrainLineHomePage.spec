Validating the Home Page of Trainline
=====================
Created by Sumit Choudhary on 17-06-2019

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.


* User Navigate to application home

Verifying the mandatory fields of Search form
----------------
tags: homepage

* User on "Home" page
* User perform "clean" action on "FromLocation" textbox
* User perform "clean" action on "DestinationLocation" textbox
* User click "GetTimeTickets" button
* Verify that "FromStationMandatoryError" value is "Please enter the station you will be travelling from"
* Verify that "ToStationMandatoryError" value is "Please enter the station you will be travelling to"
* User enter "FromLocation" as "Berlin"
* User enter "DestinationLocation" as "Namborn"
* Verify that "FromStationMandatoryError" should not be displayed
* Verify that "ToStationMandatoryError" should not be displayed


Verifying the visibility logic and functionality of Swap location button
----------------
tags: homepage

* User on "Home" page
* Verify that "SwapLocation" should not be displayed
* User enter "FromLocation" as "Berlin"
* User enter "DestinationLocation" as "Namborn"
* Verify that "SwapLocation" should be displayed
* User click "SwapLocation" button
* Verify that "FromLocation" textbox value should be "Namborn"
* Verify that "DestinationLocation" textbox value should be "Berlin"


Verifying the Past date should not be allowed
----------------
tags: homepage

* User on "Home" page
* User enter "DateOfTravel" as "17-Jun-19"
* Verify that "PastDateError" value is "Your outward journey is in the past"


Verifying the minimum and maximum no of passengers allowed
----------------
tags: homepage

* User on "Home" page
* User click "PassengerSummary" button
* Validate the valid combination of "Adults" and "Childrens"
    |AdultCount |Childrens|              message                |
    |---------- |---------|-------------------------------------|
    |     9     |  1      | Please select up to 9 travellers    |
    |     1     |  9      | Please select up to 9 travellers    |
    |     5     |  5      | Please select up to 9 travellers    |
    |     0     |  0      | Please select at least one traveller|


Verifying that age of child should be greater than 4 if travelling single
----------------
tags: homepage
* User on "Home" page
* User click "PassengerSummary" button
* User select "Adults" as "0"
* Validate the valid combination of "Childrens" and "ChildAge"
    |Childrens  |ChildAge |              message                                 |
    |---------- |---------|------------------------------------------------------|
    |     1     |  3-4    | Please select one traveller above the age of four    |
    |     1     |  0-2    | Please select one traveller above the age of four    |


Getting broken links information if any on Home Page
----------------
tags: searchTrainsLondon
* User on "Home" page
* Check if any hyperlink is broken