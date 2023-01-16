# Spring Boot "Microservice" Attornatus Project



This is a sample Java / Maven / Spring Boot (version 3.0.1) application that can be used as a starter for creating a microservice rest.

Read this in other languages: 

## How to Run [🔝](#spring-boot--microservice--impact-platafrom-project)

This application is packaged as a war which has Tomcat 10.1.4 embedded.

* Clone this repository
* Make sure you are using JDK 19.0.1 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these methods: ```mvn spring-boot:run```
  or can use any IDE.
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2023-01-06T14:11:32.770-03:00  INFO 13332 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-01-06T14:11:32.778-03:00  INFO 13332 --- [  restartedMain] c.i.project.ImpactProjectApplication     : Started ImpactProjectApplication in 4.978 seconds (process running for 5.462)
```

# Indice [🔝](#spring-boot--microservice--impact-platafrom-project)

- [Spring Boot "Microservice" Attornatus Project](#spring-boot--microservice--attornatus-project)
    - [How to Run](#how-to-run-)
        - [About the Service](#about-the-service-)
            - [Get endpoints, documentation configurations, etc.](#get-endpoints-documentation-configurations-etc-)
            - [Create a product resource](#create-a-product-resource-)
            - [list of products](#list-of-products-)
            - [By ID with UUID](#by-id-with-uuid-)
            - [Update a product resource](#update-a-product-resource-)
            - [Delete a product resource](#delete-a-product-resource-)
            - [Documentation Swagger](#to-view-swagger-2-api-docs-)
    - [About Spring Boot](#about-spring-boot-)
    - [Running the project with MySQL](#running-the-project-with-mysql-)

## About the Service [🔝](#spring-boot--microservice--attornatus-project)

The service is just a simple products review REST service, 
 It uses an in-memory database (H2) to store the data. You can
also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some
REST endpoints defined in ```br.com.desafio.attornatus.controller.PessoaController``` on **port 8080**.



You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.

Here is what this little application demonstrates:

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 9.0.34): No need to install a container separately on the host just run using the ``mvn spring-boot:run`` command
* Endpoints automatically on a configured port.
* Writing a REST service using annotation:  JSON request / response;
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations


The service has structural applications most used in the development market such as MVC architecture, TDD concept
applications, clean Code.

It was also implementing a unit testing system with JUnit5, which provides a more robust application and less prone to bugs.
as you saw above there is a command to execute all tests that were successful.



Here are some endpoints you can call:

### Get endpoints, documentation configurations, etc. [🔝](#spring-boot--microservice--attornatus-project)

```
http://localhost:8080/swagger-ui.html
http://localhost:8080/pessoas
http://localhost:8080/pessoas/:id
http://localhost:8080/pessoas/:id/enderecos
```

### Create a product resource [🔝](#spring-boot--microservice--attornatus-project)

```
POST /products
Accept: application/json
Content-Type: application/json

{
    "nome" : "João da Silva",
    "dataNascimento" : "1992-10-30"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8080/products/1
```

### list of people [🔝](#spring-boot--microservice--attornatus-project)

```
GET http://localhost:8080/pessoas

Response: HTTP 200
Content: list of application/json
```
### By ID  [🔝](#spring-boot--microservice--attornatus-project) 
```
GET http://localhost:8080/pessoas/1

Response: HTTP 200
Content: application/json


{
        "id": 1,
        "nome": "João da Silva",
        "dataNascimento": "1992-10-30",
        "enderecos": []
}
```

### Update a person resource [🔝](#spring-boot--microservice--attornatus-project)

```
PUT /pessoas/1
Accept: application/json
Content-Type: application/json

{
    
    "nome": "João da Silva",
    "dataNascimento": "1992-10-30",
    "enderecos": []
}

RESPONSE: HTTP 204 
```
### Delete a perso  resource [🔝](#spring-boot--microservice--attornatus-project)

```
DELETE /api/products/e6ec75a3-cf21-4fa5-864f-2d6144f06b4c
Content-Type: No content

RESPONSE: HTTP 204 
Content: No Content
```
### To view Swagger 2 API docs [🔝](#spring-boot--microservice--attornatus-project)

Run the server and browse to localhost:8080/swagger-ui.html



### To view your H2 in-memory datbase [🔝](#spring-boot--microservice--impact-platafrom-project)

The 'test' profile runs on H2 in-memory database. To view and query the database you can browse to http://localhost:8080/h2-console. Default username is 'sa' with a blank password. Make sure you disable this in your production profiles. For more, see https://goo.gl/U8m62X



Author                                                                                                                                                     |                                                                                                                                                                                                                                                                         |
| :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| [<img src="https://avatars.githubusercontent.com/u/23271567?v=4" width=115><br><sub>@rodrigoml21</sub>](https://github.com/rodriigolima) <br><br> | :computer: [About me](https://about.me/rmls)


