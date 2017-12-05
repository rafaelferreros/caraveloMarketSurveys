# caraveloMarketSurveys

# Caravelo Market Surveys Recruitment Assignment

Assignment
Design a beautiful REST API for our new Marketing Research System. Your design must comply with the key business requirements outlined below.

Deliverables

1. Executable system providing your proposed REST API (easy to run in Unix-based Workstations, e.g. OSX).
2. Test suite with visual feedback (could be text output) that demonstrates to us the proposed REST API capabilities.

Business Requirements 1
When Market Survey Results are seen as a commercial commodity the sourcing of Market Survey information is generally conducted in two phases:

1. Providing information on available Market Surveys. (This process can be seen to be similar to providing a catalogue of available products.) It is typically, but not always, initiated by a request for information on available Market Surveys. The request will provide information on the subject in which the requestor has an interest allowing the provider to find the most relevant Market Surveys. The provider will respond with information about the relevant Market Surveys available.

2. Providing Market Survey results. (This process can be seen to be similar to the actual ordering and delivery of a traditional product.) Typically, but not always, this process will be initiated by a request identifying the Market Surveys
to be provided. When treating Market Survey Results as a commercial commodity, the request process is assumed to be fulfilled as part of the traditional Buy-Ship-Pay process and is thus not further elaborated in this document. The provider will respond by providing the results from the requested Market Surveys as ordered. The actual data to be provided may be in a variety of formats (documents, spreadsheets, data files, etc.). The delivery of this data is not
further elaborated in this document. 
Note ➜ Providing Market Survey results falls outside the scope of this assignment, we reproduce it below only for contextual purposes.


# Provide Information on Available Data on Market Surveys

The Provide information on available Market Surveys process allows the Information Requester to send a request message (Request for information on available Market Surveys) to the Information Provider asking for information on Market Surveys available from the Information Provider. The request will provide information on the subject in which the Information Requester has an interest allowing the Information Provider to find the most relevant Market Surveys. The process further allows the Information Provider to respond with information about the relevant Market Surveys he may provide and the conditions under which they are available.

# REST API
End points

* http://localhost:8080/api/customer
* http://localhost:8080/api/provider
* http://localhost:8080/api/subscription
* http://localhost:8080/api/survey

# To request MarketSurveys information

http://localhost:8080/api/survey/provider/2/request?targetGender=ALL&targetIncomeCurrency=USD&targetIncomeL=30000&targetIncomeH=50000&targetAgeH=80&targetAgeL=50

# To add subscription

POST

http://localhost:8080/api/subscription/customer/1

{
 "channel":"MAIL",
 "frequency":"DAYLY",
 "targetGender":"MALE",
 "targetIncomeCurrency":"EUR",
 "targetAgeL":"30",
 "targetAgeH":"50",
 "targetIncomeH":"10000",
 "targetIncomeL":"50000"
}




