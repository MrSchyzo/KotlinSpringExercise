# RGiChallenge



## Made with:

- Spring as the main framework
- Spring JPA for persistence
- Kotlin as programming language
- Docker to run a DB server


## Assumptions

- A Task can be owned by multiple Users
- A User can own multiple Tasks
- A Task can be closed only by its owners
- Closing a Task is considered an idempotent operation when correctly executed
- There is not any specification about the User apart from its existence and the relation with Tasks
- A Task starts from an `OPEN` status and ends when is `CLOSED`
- An autoincrement field is sufficient to identify a Task as it is represented as a single table row


## Why I chose what I chose

### Kotlin
Kotlin is a modern JVM programming language that tends to be way less verbose than Java. I used it mainly for the least 
amount of boilerplate and because is a language that I rarely use: this challenge is a good occasion to use a little bit
of Kotlin.

### Spring and Spring JPA
It is one of the most used JVM Framework to build an application in a really short time. I can easily distribute
responsibilities between different interfaces and classes.

### Constructor Injection
I prefer it over property injection for two (and a half) reasons:
- It makes dependencies more explicit when looking at the class code
- I can make immutable classes that have dependencies
- As far as I know, Kotlin has some syntax for delegation-based composition, and that syntax looks more 
"constructor-friendly" than "property-friendly"

### Relational DB
Task and User have a relationship and RDB tend to shine when you want to ensure consistent relationships (at the 
cost of scalability)

### ReturnStatus exception
It looked the fastest way to customize exception handling in a REST API

### Docker
I do not want to install unneeded software in my machine: with docker I can run a MySQL server locally without the pain 
of maintaining software inside my computer. It is easier to speed up local manual tests as I can spin up a container 
whenever I want and throw away an unhealthy/inconsistent server if I mess something up.

### Endpoints
#### `GET /user/{userId}/tasks`
Retrieving all tasks owned by a user means that an userId must be provided in order to identify it. Exposing it as a query
parameter looks less suitable for a required parameter. Other than that, retrieving all tasks owned by a User might look
like something belonging to its details and that can justify a two-level deep path.
#### `POST /tasks`
Creating a resource is a POST request as it is not an idempotent operation. It is not tied to a particular User as a Task
might have multiple owners: specifying a User inside the path does not make too much sense.
#### `GET /tasks/{id}`
It just looks the most suitable way to declare an endpoint to retrieve a specific resource. That is all.
#### `PUT /user/{userId}/tasks/{id}`
This is my least safe choice in my opinion: I decided to make Task closure as an idempotent operation, that justifies
the HTTP verb. Regarding the URL itself, the choice is only focused on giving the User ID of an owner of the specified
task: this implementation does not consider any kind of authentication middleware between the request and its execution.
An User ID is needed in order to check whether the Task has to be closed (I check its ownership).


## Instructions
### Launch the application with a MySQL server
Just run `./launch-application.sh` and make all the requests you want at `http://localhost:8080`
### Execute unit tests
Either `mvn test` or `mvn <anygoal after test>`
