package com.mannietest.customloglevellog4j2.customloglevel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Logging {

    private static Logger logger = LogManager.getLogger();

    public void performSomeTask(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        logger.fatal("This is a fatal message");
        
        logger.log(Level.getLevel("TEST"), "a TEST message");
    }
}
