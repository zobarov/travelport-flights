# Travelport Flights

Technical Assessment project for Travelport (2020).
The purposes of the project is to deploy a gateway service to call third-party API to provide airline flights 
information and represents it to required format.
Third-Party API replies in XML-format.
Requred REST output should be converted to JSON. 

## Build and Run

Build with Gradle 6

To build application (you will need Internet access to Maven Central):

> gradle build

The gateway configured to be  simply run via Gradle hook for Spring Boot command prompt as.

> gradle bootRun

Runs on port 8080

Running JUnit test:

> gradle test


## Testing

Various approaches available:

- Curl or WebBrowser manual testing HTTP requests like:

````
http://localhost:8080/flights/availability/LED/AMS?start=2014-01-02T10:48:00.000Z&end=2015-01-02T10:48:00.000Z&pax=1
````

- Postman
You can find Postman collections for testing in src/main/resources


## Used Libraries and frameworks

- Java 11 
- Spring Boot, REST, Test
- Gradle
- JUnit 5
- Swagger

## TODOs and known issues:
- Money values. Base in use for all fares.
- Dates as string, Formatting, etc.
- FlightTime calculation
- Test coverage for border, missing values
- Test coverage for endpoint missing params
- Error handling.
