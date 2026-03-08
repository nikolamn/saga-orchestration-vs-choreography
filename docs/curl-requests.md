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
```

## Account Service
```bash
curl -X GET -i http://localhost:8080/account/actuator/health
curl -X GET -i http://localhost:8082/actuator/health
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