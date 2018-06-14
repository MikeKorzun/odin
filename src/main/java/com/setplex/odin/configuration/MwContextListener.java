package com.setplex.odin.configuration;

import ch.qos.logback.classic.LoggerContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.slf4j.LoggerFactory;

@WebListener
public class MwContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Nothing to initialize additionally
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // To avoid memory leak. See logback documentation for details
        ((LoggerContext) LoggerFactory.getILoggerFactory()).stop();
    }
}
