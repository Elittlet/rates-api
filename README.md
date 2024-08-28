# Rates-API
## Description
An API for retrieving and setting parking spot rates. Users can retrieve all current rates for various days and time ranges or find the pricing for a specific day and time.
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
  
  Allows user to retrieve a price base on the provide start and end parameters. If the start and end parameters spand more than one day or span multiple rates unavailable is returned.
  #### Example Request and Response
  ```
    GET /rates_api/v1/price?start=2015-07-01T07:00:00-05:00&end=2015-07-01T12:00:00-05:00
  ```
  ```
    {
        "price": 2000
    }
