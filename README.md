## Saga Orchestration

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
## Saga Choreography

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