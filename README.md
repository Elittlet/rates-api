# Rates-API
## Description
An API for retrieving and setting parking spot rates. Users can retrieve all current rates for various days and time ranges or find the pricing for a specific day and time. The API will boot and listen on port 5000 when ran. localhost:5000/rates_api/v1 is the full API URL.
## Endpoints
- ### `PUT /rates_api/v1/rates`
  Allows user to send a list of rates to the API to be stored. This action will replace all previously stored rates with the new rates information. Request payload must be a NON-EMPTY JSON formatted array of rates.
  #### Example Request payload
    ```
    {
    "rates": [
      {
      "days": "mon,tues,thurs",
      "times": "0900-2100",
      "tz": "America/Chicago",
      "price": 1500
      },
      {
      "days": "fri,sat,sun",
      "times": "0900-2100",
      "tz": "America/Chicago",
      "price": 2000
      }
    ]
  }
    ```
__________
- ### `GET /rates_api/v1/rates`
    Allows user to retrieve a list of all stored rate information, including the days the rates are valid, the time range the rate price is valid and the timezome for that time range.
    #### Example Response
     ```
     {
        "rates": [
                    {
                      "days": "mon,tues,thurs",
                      "times": "0900-2100",
                      "tz": "America/Chicago",
                      "price": 1500
                    },
                    {
                      "days": "fri,sat,sun",
                      "times": "0900-2100",
                      "tz": "America/Chicago",
                      "price": 2000
                    },
                    {
                      "days": "wed",
                      "times": "0600-1800",
                      "tz": "America/Chicago",
                      "price": 1750
                     },
           ]
      }
     ```
_____
- ### `GET /rates_api/v1/price`
     `Params: start, end`
  
  Allows user to retrieve a price base on the provide start and end parameters. If the start and end parameters spand more than one day or span multiple rates unavailable is returned. Start and End parameters must encoded when using special symbols such as '%2B for '+', or the values will not be passed properly in the request.
  #### Example Request and Response
  ```
    GET /rates_api/v1/price?start=2015-07-01T07:00:00-05:00&end=2015-07-01T12:00:00-05:00
  ```
  ```
    {
        "price": 2000
    }
  ```
## Requirements and how to run
The application can be unzipped and opened using an IDE (ex. Intellij) of choice and ran within the IDE. The API will start, listening on port 5000 and load the initial data into the H2 memory store database. From there users can use an API testing tool of their choice such as Postman to send requests to the above endpoints.

### Run Using Intellij or Preferred IDE
**PLEASE NOTE: This application was created using JDK 17 it is heavily advised to make sure your Java version is Java 17 for the application to run properly.**
### Example Usage
  ![Screenshot_2024-08-28_13-18-59](https://github.com/user-attachments/assets/87b0c332-94f9-4ac1-89ff-98809462ea26)
![Screenshot_2024-08-28_13-19-35](https://github.com/user-attachments/assets/7bc4c7ef-f610-4567-88fc-903e84fb3071)
_______
### Run from the command line
- Install or ensure Maven is installed: https://maven.apache.org/install.html
- Unzip rates-api and cd directory to ```/path/to/rates-api```
- ```mvn spring-boot:run```
  
![Screenshot_2024-08-28_16-33-12](https://github.com/user-attachments/assets/0cd87295-d2c6-4b5c-8019-2166878e2907)

  
