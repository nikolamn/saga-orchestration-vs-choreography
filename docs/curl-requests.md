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
    -d '{"username":"nekoNovoIme13341","password":"strongPassword","role":"HOST"}' \
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
```

## Reservation Service
```bash
curl -X GET -i http://localhost:8080/reservation/actuator/health
curl -X GET -i http://localhost:8084/actuator/health
```