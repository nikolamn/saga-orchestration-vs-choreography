# Test Endpoints

```bash
curl -X GET -i http://localhost:8080/auth/test
curl -X GET -i http://localhost:8080/account/test
curl -X GET -i http://localhost:8080/accommodation/test
curl -X GET -i http://localhost:8080/reservation/test
```

# Actuator Endpoints

## API Gateway
```bash
curl -X GET -i http://localhost:8080/actuator/health
```

## Auth Service
```bash
curl -X GET -i http://localhost:8080/auth/actuator/health
curl -X GET -i http://localhost:8081/actuator/health
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{
        "authUser": {
            "username":"nekod4d331154999344",
            "password":"strongPassword",
            "role":"HOST"
            },
        "account": {
            "firstName":"wewedfsdfsdsdss",
            "lastName":"fdfdsfsdfsdf",
            "email":"11224111@gmail.com",
            "gender":"MALE",
            "birthdate":"1990-10-10",
            "address": {
                "country":"222222",
                "city":"",
                "street":"",
                "number":"999999"
            }
        }
    }' \
    -i http://localhost:8081/auth/signup 

Grpc test
curl -i http://localhost:8081/greet?name=Gaze
```

## Account Service
```bash
curl -X GET -i http://localhost:8080/account/actuator/health
curl -X GET -i http://localhost:8082/actuator/health
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{"authUserId":"dd24dcd7-44b0-4ac5-ad7a-adf941e64ce6","firstName":"Marko","lastName":"Markovic","gender":"MALE","birthDate":"1999-09-09","email":"marko@gmail"}' \
    -i http://localhost:8082/account/create 
```

## Accommodation Service
```bash
curl -X GET -i http://localhost:8080/accommodation/actuator/health
curl -X GET -i http://localhost:8083/actuator/health
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{
        "name":"wewedfsdfsdsdss",
        "description":"fdfdsfsdfsdf",
        "address": {
            "country":"222222",
            "city":"sddsad",
            "street":"sadsad",
            "number":"999999"
        },
        "amenities":["WIFI", "BATH_TUB"],
        "ownerId":"2ee2dae2-7936-417a-beee-707dd14c6236",
        "minNumberOfGuests":"2",
        "maxNumberOfGuests":"4"
    }' \
    -i http://localhost:8083/accommodation/new 
```

## Reservation Service
```bash
curl -X GET -i http://localhost:8080/reservation/actuator/health
curl -X GET -i http://localhost:8084/actuator/health
curl -X POST \
    -H "Content-Type: application/json" \
    -d '{
        "accommodationId":"2ae2dae2-9136-417a-beee-707dd14c6236",
        "userId":"2ae2dae2-9136-433a-beee-707dd14c6236",
        "description":"fdfdsfsdfsdf",
        "beginning":"2026-10-10",
        "ending":"2026-11-10",
        "guests":"2"
    }' \
    -i http://localhost:8084/reservation/new 
```