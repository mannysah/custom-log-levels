# Creating Custom Log Levels using Log4j
* [Log4j 1.2](#log4j-v1-2)
* [Log4j 2](#log4j-v2)

**Given** there is project build using Java
**When** the user needs to log certain statements
**And** those statements do not fall into default logging levels
**And** those statements should log at a certain level
**Then** developers needs to create custom log level for the application 


I have come across multiple scenarios where we need a little more logging in production where ERROR logging level is not appropriate. Sometimes, we need some more (very little) information on what was going on during the transaction. However, logging level INFO which is perhaps designed for higher environment than development and lower than production becomes too verbose for production.


## Prerequisite
* Understanding of Java (Version used for this is Java 8)
* Understanding of log4j
* This project uses maven, but same thing can be achieved using gradle or other build tools. 

## How to test this
Navigate the to the directory of the version you want to test and run below maven command. 

```
mvn clean test
```

# Log4j v1.2
* Create a Custom Log Level
    * [FunnyLogLevel.java](log4j12/src/main/java/com/mannietest/customloglevellog4j12/customloglevel/FunnyLogLevel.java)
      This class defines the custom log level with the name **FUNNY**. The log level of **FUNNY** is lesser than **ERROR** log level, so when the log level is set to ERROR this log level is covered for output. This is achieved by setting the *int* value of **FUNNY** using below code. 
    
      ```
      public static final int FUNNY_INT = ERROR_INT + 10;
      ```

* [log4j.properties](log4j12/src/main/resources/log4j.properties) is set to **ERROR**

  ```
	log4j.rootLogger=ERROR, FUNNY
  ```

 
* We are ready to log it. In [FunnyLogging.java](log4j12/src/main/java/com/mannietest/customloglevellog4j12/customloglevel/FunnyLogging.java)

  When we run the method in this class. We will see the message printed using Line#1 and Line#2 and not the Line#3.
  
        //Print the below two messages when in ERROR Log level
        logger.log(FunnyLogLevel.FUNNY, "I am a Funny log, because the Log level set is ERROR and my log level is FUNNY!!"); //Line#1
        logger.error("I am an ERROR message");                                                                               //Line#2
        
        //Do not print this in ERROR Log level
        logger.log(Level.DEBUG, "I am a DEBUG message");	                                                                 //Line#3

* There is a test class [FunnyLoggingtest.java](log4j12/src/test/java/com/mannietest/customloglevellog4j12/customloglevel/FunnyLoggingTest.java) to test this.

 
Output would look something like this:

	```
	2018-08-22 16:20:05 FUNNY FunnyLogging:13 - I am a Funny log, because the Log level set is ERROR and my log level is FUNNY!!
	2018-08-22 16:20:05 ERROR FunnyLogging:14 - I am an ERROR message
	```
  
# Log4j v2
Log4j2 makes this even easier. Now, we don't need the class defining the custom log level. We can still do it, if we want other capabilities. For the simplest use case, we don't need it.

*For this example, we are using JSON as the log4j2 properties file. Same thing can be done using properties, XML, YAML files*

* Define the custom log level in [JSON log4j2 file](log4j2/src/test/resources/log4j2.json) and the log level to be **ERROR**

    ...
    "customLevels" : {
      "CustomLevel" : {
        "name": "ShowMeOnError",
        "intLevel" : "190"
      }
    },
    ...

    ...
    "loggers": {
      "logger": {
        "name": "com.mannietest.customloglevellog4j2.customloglevel",
        "level": "error",
        "appender-ref": [{"ref": "File-Appender", "level":"error"}]
      },
    ...

* Ready to log it. As done in [Logging.java](log4j2/src/main/java/com/mannietest/customloglevellog4j2/customloglevel/Logging.java).
  
  When we run the method, we will see only **Line#4** , **Line#5** and **Line#6**.
  
        logger.debug("This is a debug message");                                              //Line#1
        logger.info("This is an info message");                                               //Line#2
        logger.warn("This is a warn message");                                                //Line#3
        
        logger.error("This is an error message");                                             //Line#4
        logger.fatal("This is a fatal message");                                              //Line#5
        
        logger.log(Level.getLevel("ShowMeOnError"), "a ShowMeOnError Custom Level message");  //Line#6  

* There is a test class [Loggingtest.java](log4j2/src/test/java/com/mannietest/customloglevellog4j2/customloglevel/LoggingTest.java) to test this.

   Output would look something like this:
    
	```
	[ERROR] 2018-08-22 15:00:39.449 [main] Logging - This is an error message
	[FATAL] 2018-08-22 15:00:39.449 [main] Logging - This is a fatal message
	[ShowMeOnError] 2018-08-22 15:00:39.449 [main] Logging - a ShowMeOnError Custom Level message
	```

Here we go with custom log levels using Log4j.

## License

MIT © Mandeep "Manny" Sah 
