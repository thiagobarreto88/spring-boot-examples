package com.example.restservice.log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.qos.logback.classic.spi.ILoggingEvent;

public class PatternMaskingLayout extends ch.qos.logback.classic.PatternLayout {
 
   private static Pattern multilinePattern;
   private static List<String> maskPatterns = new ArrayList<>();
   
   private static final String MASK = "************$2";
 
   public void addMaskPattern(String maskPattern) { // invoked for every single entry in the xml
       maskPatterns.add(maskPattern);
       multilinePattern = Pattern.compile(
               maskPatterns.stream()
                       .collect(Collectors.joining("|")), // build pattern using logical OR
               Pattern.MULTILINE
       );
   }
 
   @Override
   public String doLayout(ILoggingEvent event) {
       return maskMessage(super.doLayout(event)); // calling superclass method is required
   }
 
   private String maskMessage(String message) {
       if (multilinePattern == null) {
           return message;
       }
       StringBuilder sb = new StringBuilder(message);
       Matcher matcher = multilinePattern.matcher(sb);
       while (matcher.find()) {   	   
    	   message = matcher.replaceAll(MASK);

       }
       return message;

   }
 
}