package top.wisely.learningspringmvc.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
//@WebListener
public class CustomListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("--CustomListener/contextInitialized---");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("--CustomListener/contextDestroyed---");
    }
}
