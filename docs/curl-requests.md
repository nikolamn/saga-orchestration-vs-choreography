## API Gateway
```bash
curl -X GET -i http://localhost:8080/actuator/health
curl -X POST -i http://localhost:8080/signup  \
    -H "Content-Type: application/json" \
    -d '{
        "user": {
            "username":"testUsername123",
            "password":"testPassword123"
            },
        "account": {
            "firstName":"John",
            "lastName":"Doe",
            "email":"johndoe1@gmail.com",
            "birthdate":"1990-10-10"
        }
    }'
curl -X POST -i http://localhost:8080/signin  \
    -H "Content-Type: application/json" \
    -d '{
        "username":"testUsername123",
        "password":"testPassword123"
        }'
curl -X GET -i http://localhost:8080/account  \
    -H "Authorization: Bearer <TOKEN>" 
curl -X DELETE -i http://localhost:8082/account/delete  \
    -H "Authorization: Bearer <TOKEN>" 
```