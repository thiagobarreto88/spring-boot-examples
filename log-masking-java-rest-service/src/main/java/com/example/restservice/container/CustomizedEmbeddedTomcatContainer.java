package com.example.restservice.container;

import java.io.CharArrayWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.catalina.valves.AbstractAccessLogValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CustomizedEmbeddedTomcatContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	
    private static final String MASK = "************$2";
    
    @Override
    public void customize(TomcatServletWebServerFactory factory) {

        System.out.println("configuring embedded Tomcat");
        TomcatSlf4jAccessValve accessLogValve = new TomcatSlf4jAccessValve();
        accessLogValve.setEnabled(true);
   
        accessLogValve.setPattern("%t %I %h %l %u %r %s %b [%D ms] %{Authorization}i %{X-Correlation-ID}i %{User-Agent}i");

        factory.addContextValves(accessLogValve);
    }

    public static class TomcatSlf4jAccessValve extends AbstractAccessLogValve {

        Logger httpAccessLogLogger = LoggerFactory.getLogger("accesslog");

        @Override
        protected void log(CharArrayWriter message) {

        	String logMessage = message.toString();
        	String regex = "([0-9]{12})([0-9]+)";
        	Pattern pattern = Pattern.compile(regex);
        	Matcher matcher = pattern.matcher(logMessage);

        	String maskedMessage = null;
            while (matcher.find()) {   	   
            	maskedMessage = matcher.replaceAll(MASK);
            }
            
            if(!StringUtils.isEmpty(maskedMessage)) {
                httpAccessLogLogger.info(maskedMessage);
            }else {
                httpAccessLogLogger.info(message.toString());
            }

        }

    }
}