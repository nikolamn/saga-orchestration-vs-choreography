# [# Saga Patterns: orchestration / choreography](https://learn.microsoft.com/en-us/azure/architecture/patterns/saga) 
A Spring Boot microservice application handling user account creation and deletion using Saga approaches such as orchestration and choreography

## 1. Saga orchestration (registration)
**Communication:** synchronous / gRPC  
**Coordinator:** `Auth-Service` (`RegistrationService`)

Used for **user registration** where immediate consistency was required. The `Auth-Service` was central orchestrator, managing a transaction that spans both the local Auth database and the remote Account service
```text
   +----------------+          +---------------------------------------------------------------+
   |     Client     |          |                          API Gateway                          |
   |                +--------->|                         (Spring Cloud)                        |
   |  POST /signup  |          |                                |                              |
   |  POST /signin  |          +--------------------------------|------------------------------+
   +----------------+                                           |
                                                                |
                               +--------------------------------v------------------------------+
                               |                           Auth-Service                        |
                               |                                                               |
                               |  AuthController ----> RegistrationService ----> UserService   |
                               |                        (Orchestrator)                |        |
                               |                                |                     |        |
                               |                                v                     v        |
                               |                      AccountServiceAdapter       PostgreSQL   |
                               |                                |                  (Auth DB)   |
                               +--------------------------------|------------------------------+
                                                                |
                                                                | gRPC / Proto
                                                                |
                               +--------------------------------v------------------------------+
                               |                         Account-Service                       |
                               |                                                               |
                               |                      AccountGrpcController                    |
                               |                                |                              |
                               |                                v                              |
                               |                        AccountGrpcService                     |
                               |                                |                              |
                               |                                v                              |
                               |                          AccountService ----> PostgreSQL      |
                               |                                                (Acc DB)       |
                               +---------------------------------------------------------------+
```
## 2. Saga Choreography (user account deletion)
**Communication:** asynchronous / NATS    
**Broker subjects:** `user.deletion.*`

Used for account deletion to prioritize decoupling. Services communicate via message broker; the Account-Service initiates "deletion request" event, and the Auth-Service reacts to perform credential cleanup

```text
   +----------------+          +---------------------------------------------------------------+
   |     Client     |          |                          API Gateway                          |
   |                +--------->|                         (Spring Cloud)                        |
   | DELETE /delete |          |                                |                              |
   |                |          +--------------------------------|------------------------------+
   +----------------+                                           |
                                                                |
                               +--------------------------------v------------------------------+
                               |                         Account-Service                       |
                               |                                                               |
                               |    AccountController                                          |
                               |            |                                                  |
                               |            v                                                  |
                               |  AccountDeletionService ----------------> AccountService      |
                               |                                |                |             |
                               |                                |                |             |
                               |        (user.deletion.request) |                v             |
                               |                                v            PostgreSQL        |
                               |                         PublishService       (Acc DB)         |
                               |                        (NATS Producer)                        |
                               +--------------------------------|------------------------------+
                                                                |
                                                                | NATS Message Broker
                                                                | (Pub/Sub)
                                                                |
                               +--------------------------------v------------------------------+
                               |                           Auth-Service                        |
                               |                                                               |
                               |  UserDeletionSubscriber ----------> UserDeletionService       |
                               |     (NATS Consumer)                  |              |         |
                               |                                      |              |         |
                               |                                      |              |         |
                               |                                      v              v         |
                               |               PostgreSQL <---- UserService   PublishService   |
                               |                (Auth DB)                     (NATS Producer)  |
                               +-----------------------------------------------------|---------+
                                                                                     |
                                                    (user.deletion.completed)        |
                                 <---------------------------------------------------+
```

## Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.5 |
| **API Gateway** | Spring Cloud Gateway |
| **Communication** | gRPC 1.51 |
| **Messaging** | NATS 2.10 |
| **Migrations** | Flyway |
| **Security** | Spring Security & JJWT |
| **Database** | PostgreSQL 18.3 |
| **ORM** | Spring Data JPA / Hibernate |
| **Containerization** | Docker engine / Docker Compose |
| **Testing** | JUnit 5, Mockito, AssertJ |

## Running with Docker Compose
`docker compose up -d`    

Executes a multi-stage build and launches the API gateway and all backend microservices along with their databases and the NATS broker.


## Environment Variables

| Variable | Description |
| :--- | :--- |
| `AUTH_SERVICE_URL` | Auth service endpoint |
| `ACCOUNT_SERVICE_URL` | Account service endpoint |
| `DB_USER` | Database username |
| `DB_PASSWORD` | Database password |
| `AUTH_DB_NAME` | Auth database name |
| `AUTH_DB_URL` | Auth database connection string |
| `ACCOUNT_DB_NAME` | Account database name |
| `ACCOUNT_DB_URL` | Account database connection string |
| `JWT_SECRET` | Key for JWT signing |
| `GRPC_CLIENT` | gRPC client address |
| `NATS_URL` | NATS broker URL |


## API Endpoints

### User signup
Creates a new user and account profile in a single transaction
```    
curl -X POST -i http://localhost:8080/signup \
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
```

### User signin
Logs the user in and gives back a JWT token for future requests 
```   
curl -X POST -i http://localhost:8080/signin \
    -H "Content-Type: application/json" \
    -d '{
        "username":"testUsername123",
        "password":"testPassword123"
    }'
```


### Get account info
Fetches the account details of the currently authenticated user via token     
```
curl -X GET -i http://localhost:8080/account \
    -H "Authorization: Bearer <TOKEN>"
```
### Delete user account
Starts the process to permanently remove a user's account from the system
```
curl -X DELETE -i http://localhost:8080/account/delete  \
    -H "Authorization: Bearer <TOKEN>" 
```
