# Hello World Service
A relatively simple project to demonstrate some basic features of a Dockerized Spring Boot REST web service.

## Features
   * /health endpoint provided by the [Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready) plugin
   * /swagger endpoint provided by the [Spring Fox](http://springfox.github.io/springfox/) plugin
   * Database entities courtesy of GORM plugin
   * Tests written in Groovy/Spock and enabled by Spock and Spring Boot testing frameworks.

## Gradle note:
All gradle commands in this document assume that you've got Gradle installed.  If you don't have gradle installed, you can use the Gradle wrapper by replacing `gradle` with `./gradlew`.
The first time you run the Gradle wrapper, it'll take some time to download the Gradle base package and dependencies; subsequent runs will be much quicker.

## Building and testing the service
Run the Gradle bootRun command to fire up the service.  Use ctrl+C to shut it down.

    gradle bootRun
    
Run all tests:

    gradle test

Clean up:

    gradle clean
    
Build a "thick" jar with embedded Tomcat:

    gradle build
   
Build a deployable war file:

    gradle war
    
Build a Docker image for the deployable war file (based on the tomcat:8.0 image)

    docker build -t steasdal/hello-world .
    
## Running the service
To run the service from gradle, simply execute the bootRun command:

    gradle bootRun
    
To run the docker image (after executing the `docker build` command above), run the following command:
    
    docker run -p 8080:8080 -d steasdal/hello-world
    
Running this Docker image will fire up the WAR in a Dockerized instance of Tomcat.  The root URL will be as follows:

   * `http://localhost:8080/hello-world/`
    
## Using the service
Once the service is up and running, fire up Curl or your favorite REST browser (e.g. Postman) and hit the following endpoints:

   * [http://localhost:8080/hello-world/hello](http://localhost:8080/hello-world/hello) - returns a "hello world" style greeting as a bit of JSON
   * [http://localhost:8080/hello-world/health](http://localhost:8080/hello-world/health) - returns a bit of JSON that describes the current of health of this service (provided by the Spring Boot Actuator)
   * [http://localhost:8080/hello-world/person](http://localhost:8080/hello-world/person) - create or browse Person records
      * POST to create a new person.
         * set Content-Type header to `application/json`
         * set the request body thusly: `{"name":"joe", "age":47}`
      * GET to retrieve a list of all people
      * GET with /{personId} to retrieve info on a particular Person
   * [http://localhost:8080/hello-world/swagger](http://localhost:8080/hello-world/swagger) - display live swagger API documentation (use your regular ol' browser for this)

## Continuous Integration Build
This project builds automatically on [Travis CI](https://travis-ci.org/).  Build results are available here:

   * [https://travis-ci.org/steasdal/hello-world](https://travis-ci.org/steasdal/hello-world)
    
## Docker Image
Successful builds result in a fresh docker image pushed to the official Docker Hub:

   * [https://hub.docker.com/r/steasdal/hello-world/](https://hub.docker.com/r/steasdal/hello-world/)
   
