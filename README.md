# Simple Rest API project in Java.

Description: Application is written in Java using maven. 

Java application that allows a user to query and show details for a given UK postcode. The app  use the freely available (no API key needed) REST API at http://postcodes.io
application to directly interact with a JSON-formatted REST API.

Details:

· Console output only – no UI .
·  application  accept a postcode as an argument to its main() method.
· It query the API to:
    Validate the postcode parameter – invalid postcodes should produce an error message
    Print the country and region for that postcode.
    Print a list of the nearest postcodes, and their countries and regions.
    
 · API methods of interest:
    GET /postcodes/{POSTCODE}
    GET /postcodes/{POSTCODE}/validate
    GET /postcodes/{POSTCODE}/nearest

· Application carefully consider error handling (eg API failure / timeout, invalid input etc)

· 2 Unit Test Cases for testing the application.


Pre Requisite:
  Maven should be installed
  command line post code argument e.g  mvn exec:java  -Dexec.args="CB30FA"


