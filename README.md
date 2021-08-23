# Clip Api Challenge

The Api has the purpose of accept Payments Request, process Disbursements.

## Technologies
- Spring Boot
- JPA
- H2
- Maven
- Java 11

Running the Api

1.- Use clip-0.0.1-SNAPSHOT.jar

2.- Execute comand line: java -jar clip-0.0.1-SNAPSHOT.jar

3.- Validate http://localhost:8080/api/clip/report/1

Web API Resources

CREATE_PAY_LOAD POST /api/clip/createPayload REQUEST

{
    "userId": 1,
    "amount": 1000
}

RESPONSE:

{
    "id": 3,
    "amount": 1000,
    "userId": 1,
    "createTs": "2021-08-23T00:49:55.420+00:00",
    "lastTs": "2021-08-23T00:49:55.420+00:00",
    "status": "NEW"
}

GET_USER_WITH_PAYMENTS GET /api/clip/usersPayments RESPONSE

[
    {
        "id": 1,
        "name": "Cristian Escamilla",
        "createTs": "2021-08-20T05:00:00.000+00:00",
        "lastTs": "2021-03-17T06:00:00.000+00:00"
    },
    {
        "id": 2,
        "name": "Lizette Mendiola",
        "createTs": "2021-08-20T05:00:00.000+00:00",
        "lastTs": "2021-03-17T06:00:00.000+00:00"
    }
]

DISBURSMENT_PROCESS POST /api/clip/disbursementProcess RESPONSE

[
    {
        "id": 1,
        "amount": 101.55,
        "userId": 1,
        "payment": 105.10,
        "createTs": "2021-08-23T00:56:44.666+00:00",
        "lastTs": "2021-08-23T00:56:44.666+00:00"
    },
    {
        "id": 2,
        "amount": 1.06,
        "userId": 2,
        "payment": 1.10,
        "createTs": "2021-08-23T00:56:44.696+00:00",
        "lastTs": "2021-08-23T00:56:44.696+00:00"
    }
]

REPORT GET /api/clip/report/{id}  RESPONSE

{
    "idUsuario": 1,
    "name": "Cristian Escamilla",
    "payments_sum": 1,
    "new_payments": 1,
    "new_payments_amount": 105.10
}



##Notes:

The following files have the configuration of the local H2 DB:

data.sql.-has all the DML inserts for all the tables
schema.sql.-has all the DDL objects in the DB