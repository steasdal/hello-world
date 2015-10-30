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
    
Build a Docker image for the "thick" jar with embedded tomcat (based on the java:8 image)

    gradle buildDockerThick
    
Build a Docker image for the war (based on the tomcat:8.0 image)

    gradle buildDockerThin
    
## Running the service
To run the service from gradle, simply execute the bootRun command:

    gradle bootRun
    
To run the thick jar version of the service from Docker (after executing the `buildDockerThick` target), run the following command:

    docker run -p 8080:8080 -d steasdal/hello-world-thick
    
To run the thin version of the service from Docker (after executing the `buildDockerThin` target), run the following command:

    docker run -p 8080:8080 -d steasdal/hello-world-thin
    
Running this Docker image will fire up the WAR in an instance of Tomcat.  The root URL will be as follows:

   * `http://localhost:8080/hello-world/` - (substitute this for the base url of the endpoints in the next section if you're running this Docker target).
    
## Using the service
Once the service is up and running, fire up Curl or your favorite REST browser (e.g. Postman) and hit the following endpoints:

   * [http://localhost:8080/hello](http://localhost:8080/hello) - returns a "hello world" style greeting as a bit of JSON
   * [http://localhost:8080/health](http://localhost:8080/health) - returns a bit of JSON that describes the current of health of this service (provided by the Spring Boot Actuator)
   * [http://localhost:8080/person](http://localhost:8080/person) - create or browse Person records
      * POST to create a new person.
         * set Content-Type header to `application/json`
         * set the request body thusly: `{"name":"joe", "age":47}`
      * GET to retrieve a list of all people
      * GET with /{personId} to retrieve info on a particular Person
   * [http://localhost:8080/swagger](http://localhost:8080/swagger) - display live swagger API documentation (use your regular ol' browser for this)
