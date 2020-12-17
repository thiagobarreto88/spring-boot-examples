package com.example.restservice.log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class Log4jPatternMaskingLayout  extends PatternLayout {
	
    private static final String MASK = "************$2";
    
    //private static final Pattern PATTERN = Pattern.compile("\\/customer\\/([0-9]{2})([0-9]{5})([0-9]{4})");
    private static final Pattern PATTERN = Pattern.compile("([0-9]{12})([0-9]+)");
     
    @Override
    public String format(LoggingEvent event) {
        if (event.getMessage() instanceof String) {
            String message = event.getRenderedMessage();
            Matcher matcher = PATTERN.matcher(message);

            if (matcher.find()) {
            	
        		 String maskedMessage = matcher.replaceAll(MASK);
                 @SuppressWarnings({ "ThrowableResultOfMethodCallIgnored" })
                 Throwable throwable = event.getThrowableInformation() != null ? 
                         event.getThrowableInformation().getThrowable() : null;

                 LoggingEvent maskedEvent = new LoggingEvent(event.fqnOfCategoryClass, Logger.getLogger(event.getLoggerName()), event.timeStamp, event.getLevel(), maskedMessage, throwable);
                 return super.format(maskedEvent);
               
            }
        }
        return super.format(event);
    }
}