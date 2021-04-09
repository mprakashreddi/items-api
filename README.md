#####
#####
    Items API with SpringWebflux and  H2 DB
    TO-DO : Implement R2DBC and Replace Tomact with Netty
    
#####
    Tech Stack
        Java 8
        SpringBoot 2
        SpringWebFlux
        JPA
        H2DB
        OpenAPI3/Swagger
        Maven
        Tomcat

######

    Build Instructions
        mvn(mvnw) clean install
        mvn(mvnw) spring-boot:run
     Swagger UI : http://localhost:8080/swagger-ui.html
     Actuator : http://localhost:8080/actuator/
     
  ####
   
    Use cases
        Item
            - Create
            - Update
            - findById
            - findAll
         File Processing
            - Process
            - List Files for given directory
            - Transaction details by transactionId