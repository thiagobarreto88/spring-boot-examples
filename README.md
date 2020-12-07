# spring-boot-examples

Spring boot sample applications

## log-masking-java-rest-service

 Spring Boot service exemplifying how to mask sensitive data in application logs and tomcat access log by creating custom logback pattern layouts and customizing tomcat embedded container.
  
  The requirement is to mask the first 12 characters in the credit card number received in the service URL in the application and access log files.
  
  Request example: http://localhost:8080/2221006244730608
  
  ### Application Log file
  For the application log file a custom pattern layout is created (Class PatternMaskingLayout) to override the logback default logging to replace the first 12 digits with '\*'  using a regular expression. The class PatternMaskingLayout is set in the logback-spring.xml file.
  
### Tomcat access log
  For the tomcat access log file a custom container is created (Class CustomizedEmbeddedTomcatContainer) and the log method is overwritten to replace the first 12 digits with "\*" using a regular expression.
  
  Application log output:
    ```2020-12-07 21:44:00.805 DEBUG 7840 --- [http-nio-8080-exec-6] c.e.restservice.CustomerController       : Credit card number: ************0608```
  
  Access log output:
   
   ```[07/Dec/2020:21:44:00 -0200] http-nio-8080-exec-6 0:0:0:0:0:0:0:1 - - GET /customer/************0608 HTTP/1.1 200 56 [7 ms] - - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36```
   
 ---
### Running the service

 1. Clone the repository
 1. Build the project using the maven command: ```mvn clean install```
 1. Run the jar using the command: ```java -jar log-masking-java-rest-service-0.0.1-SNAPSHOT.jar```
 1. Access the URL ```http://localhost:8080/customer/<Some credit card number>```
 1. Check the console log.
 
### Built With
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot)
