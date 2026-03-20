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
        "user": {
            "username":"myGod11223344",
            "password":"myGod11223344",
            "role":"HOST"
            },
        "account": {
            "firstName":"wewedfsdfsdsdss",
            "lastName":"fdfdsfsdfsdf",
            "email":"mygod@gmail.com",
            "gender":"MALE",
            "birthdate":"1990-10-10",
            "address": {
                "country":"222222",
                "city":"sadsad",
                "street":"sdfsd",
                "number":"999999"
            }
        }
    }' \
    -i http://localhost:8081/auth/signup 
    
curl -X POST http://localhost:8081/auth/signin  \
    -H "Content-Type: application/json" \
    -d '{
        "username":"myGod11223344",
        "password":"myGod11223344"
        }
    }' \
    -i 

curl -i -X PATCH http://localhost:8081/user/update \
    -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3NzM5ODA5MzgsImV4cCI6MTc3Mzk4NDUzOCwianRpIjoiNGEyOGFjMTEtZWUwYi00ZWViLTg0N2UtMDVhYTMzMWYxYmVjIiwic3ViIjoiOTJmM2M5NTEtMzhjNi00M2E4LWIwNDQtYzFhZDg2OGE4M2M3IiwidXNlcm5hbWUiOiJteUdvZDExMjIzMzQ0Iiwicm9sZSI6WyJST0xFX0hPU1QiXX0.5psJu_oPTaPgbIttqE6K4QbqowOy6tkXAR6eWN5pGEJBm7dn_NDpsi4e-5umes0c3vCMgpYR7GPh92qwtW-L8Q" \
    -H "Content-Type: application/json" \
    -d '{
        "user": {
            "username": "aaaaa555555aaaa"
        },
        "account": {
            "firstName": "ramdp,",
            "lastName": "letstry",
            "gender": "MALE"
        }
    }'

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