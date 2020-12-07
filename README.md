# spring-boot-examples

Spring boot sample applications

##log-masking-java-rest-service: Spring Boot service exemplifying how to mask sensitive data in application logs and tomcat access log by creating custom logback pattern layouts
  
  The requirement is to mask the first 12 characters in the credit card number receveid in the service URL in the application and access log files.
  
  Request example: http://localhost:8080/2221006244730608
  
  Application log output:
    2020-12-07 21:44:00.805 DEBUG 7840 --- [http-nio-8080-exec-6] c.e.restservice.CustomerController       : Credit card number: ************0608
  
  Access log output:
   
   [07/Dec/2020:21:44:00 -0200] http-nio-8080-exec-6 0:0:0:0:0:0:0:1 - - GET /customer/************0608 HTTP/1.1 200 56 [7 ms] - - Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36
   

  For the application log file a custom pattern layout is created (Class PatternMaskingLayout) to override the logback default logging to replace the first 12 digits with '\*'\  using a regular expression. The class PatternMaskingLayout is set in the logback-spring.xml file.
  
  
  For the tomcat access log file a custom container is created (Class CustomizedEmbeddedTomcatContainer) and the log method is overwritten to replace the first 12 digits with "\*"\ using a regular expression.
