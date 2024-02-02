package com.chinexboroja.logs.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // Include contextual information using Logback by importing MDC library
        MDC.put("Id", "7890"); // Add new items
        logger.info("order placed");

        MDC.put("buyerName", "Chinedu");
        MDC.put("destination", "Village Square");

        logger.info("Order shipped successfully");

//        // Remove items
        MDC.remove("buyerName");
        MDC.remove("destination");

        logger.warn("Order shipment failed");

        // Clear all items
        MDC.clear();

        // System.setProperty("LOG_LEVEL", "warn");
//        logger.trace("Entering method foo()");
//        logger.debug("Received request from 198.12.34.56");
//        logger.info("User logged in: Chinedu");
//        logger.warn("Connection to server lost. Retrying...");
//        logger.error("Failed to write data to file: myFile.txt");
    }
}
