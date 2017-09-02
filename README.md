# Reservation Service

The Reservation [Spring Boot](https://projects.spring.io/spring-boot/) Service is a RESTful Web Service, backed by [H2 database](https://www.h2database.com/).
Reservation is a first-class citizen of the system. Reservation entity contains: first & last name of the guest, room number, start date and end date of the stay.
API users can perform basic CRUD operations for Reservation entities and search Reservations by date range.

## Build and Run the application

The Reservation service will be running on `8081`. To build, test, and run the Reservation service as a JAR file:

```bash
./gradlew clean build
java -jar build/libs/reservation-service-0.0.1-SNAPSHOT.jar
```

## Reservation Service Endpoints
The Reservation service exposes several HTTP API endpoints, listed below.

Description                             | Method  | Endpoint
--------------------------------------- | ------- | -------------------------------------------------------------
List Reservations                       | GET     | [/reservations](http://localhost:8081/reservations)
Search Reservations                     | GET     | [/reservations](http://localhost:8081/reservations)
View Reservation                        | GET     | [/reservations/{id}](http://localhost:8081/reservations/{id})
Create Reservation                      | POST    | [/reservations](http://localhost:8081/reservations)
Update Reservation                      | PUT     | [/reservations/{id}](http://localhost:8081/reservations/{id})
Delete Reservation                      | DELETE  | [/reservations/{id}](http://localhost:8081/reservations/{id})
Delete Reservation                      | DELETE  | [/reservations/{id}](http://localhost:8081/reservations/{id})

## Swagger
[Swagger](http://swagger.io/) is used to describe and document RESTful APIs.
To access swagger UI, go to: http://localhost:8081/swagger-ui.html

## Sample Requests

Create a new reservation:

[HTTPie](https://httpie.org/) is used for samples.

```bash
http POST http://localhost:8081/reservations \
  firstName="Donald" \
  lastName="Trump" \
  roomNumber=1201  \
  startDate="2017-09-01" \
  endDate="2017-09-10"
```

List all reservations:

```bash
http GET http://localhost:8081/reservations
```

Find by date range:

```bash
http GET 'http://localhost:8081/reservations/query?start=2017-09-04&end=2017-09-06'
```

## Sample Responses

List of pre-populated reservations:

```json

    {
        "endDate": "2017-09-03",
        "firstName": "Elon",
        "id": 1,
        "lastName": "Musk",
        "roomNumber": 101,
        "startDate": "2017-09-01"
    },
    {
        "endDate": "2017-09-06",
        "firstName": "Chris",
        "id": 2,
        "lastName": "Hadfield",
        "roomNumber": 101,
        "startDate": "2017-09-04"
    },
    {
        "endDate": "2017-09-07",
        "firstName": "Jeff",
        "id": 3,
        "lastName": "Bezos",
        "roomNumber": 102,
        "startDate": "2017-09-01"
    },
    {
        "endDate": "2017-09-14",
        "firstName": "Travis",
        "id": 4,
        "lastName": "Kalanick",
        "roomNumber": 102,
        "startDate": "2017-09-08"
    }
]
```