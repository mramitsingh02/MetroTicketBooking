# MetroTicketBooking
Metro Ticket Booking
#### Application is to book the metro ticket for a passenger.
#### build to calculate price for between the stations.


### Route Service
#### ----------------------------------------------------
#### Get All Routes
#### GET - http://localhost:6666/route/routes
#### ----------------------------------------------------
#### Create new route
#### POST - http://localhost:6666/route
##### Request
##### 
    {
        "routeName": "Red Line",
        "routeNickName": "Red"
    }
##### Response
##### 
    [
        {
            "routeId": 1,
            "routeName": "Red Line",
            "routeNickName": "Red",
            "stations": null,
            "createdOn": "2023-10-25T18:49:19.650507",
            "updatedOn": "2023-10-25T18:49:19.650507"
        }
    ]
#### ----------------------------------------------------

#### Create new station under the root
#### POST - http://localhost:6666/route/100
##### Request
##### 
    {
        "stationName": "Huda City Center",
        "stationNickName": "HCC",
        "distance": 3
    }
##### Response
##### 
    {
    "stationId": 1,
    "stationName": "Huda City Center",
    "stationNickName": "HCC",
    "previousStationId": null,
    "nextStationId": null,
    "distance": 3.0,
    "routeId": 100
    }

#### ----------------------------------------------------
#### Get Stations of route
#### GET - http://localhost:6666/route/100

    {
    "routeId": 100,
    "routeName": "Red",
    "routeNickName": "Red Line",
    "stations": [
        {
            "stationId": 1001,
            "stationName": "Huda City Center",
            "stationNickName": "HCC",
            "nextStationId": {
                "stationId": 1002,
                "distance": 0.0
            },
            "distance": 3.0,
            "routeId": 100
        },
        {
            "stationId": 1002,
            "stationName": "Iffco chowk",
            "stationNickName": "IFFCO",
            "previousStationId": {
                "stationId": 1001,
                "distance": 0.0
            },
            "nextStationId": {
                "stationId": 1003,
                "distance": 0.0
            },
            "distance": 3.0,
            "routeId": 100
        },
        {
            "stationId": 1003,
            "stationName": "M.G. ROAD",
            "stationNickName": "M.G.ROAD",
            "previousStationId": {
                "stationId": 1002,
                "distance": 0.0
            },
            "nextStationId": {
                "stationId": 1004,
                "distance": 0.0
            },
            "distance": 3.0,
            "routeId": 100
        }
    ]
    }

#### ----------------------------------------------------
#### Ticket Booking
#### POST - http://localhost:6666/ticket/

###### Note: passengerType : ANONYMOUS/STAFF
###### For STAFF no charge

#### Request Body
    {
    "bookingStationId": 1001,
    "sourceStationId": 1001,
    "destinationStationId": 1010,
    "passengerType": "ANONYMOUS",
    "paymentType": "CASH"
    }
#### Response Body

    {
    "ticketId": 9,
    "bookingStationId": 1001,
    "passengerType": "ANONYMOUS",
    "sourceStationId": 1001,
    "destinationStationId": 1010,
    "generateOn": "2023-10-25T22:50:33.977313",
    "price": 91.0,
    "paymentType": "CASH"
    }

#### ----------------------------------------------------

#### Request Body
    {
    "bookingStationId": 1001,
    "sourceStationId": 1001,
    "destinationStationId": 1010,
    "passengerType": "STAFF",
    "paymentType": "CASH"
    }
#### Response Body

    {
    "ticketId": 9,
    "bookingStationId": 1001,
    "passengerType": "STAFF",
    "sourceStationId": 1001,
    "destinationStationId": 1010,
    "generateOn": "2023-10-25T22:50:33.977313",
    "price": 0.0,
    "paymentType": "CASH"
    }

#### ----------------------------------------------------
#### Trip Start
#### PUT - http://localhost:6666/trip/1/start


#### Response Body

    {
    "ticketId": 1,
    "passenger": "ANONYMOUS",
    "distanceCover": 0.0,
    "price": 91.0,
    "tripType": "START",
    "startOn": "2023-10-25T22:58:35.6630075"
    }

#### ----------------------------------------------------
#### Trip Stop
#### PUT - http://localhost:6666/trip/1/stop


#### Response Body

    {
    "ticketId": 1,
    "passenger": "ANONYMOUS",
    "distanceCover": 0.0,
    "price": 91.0,
    "tripType": "START",
    "startOn": "2023-10-25T22:58:35.6630075"
    }
