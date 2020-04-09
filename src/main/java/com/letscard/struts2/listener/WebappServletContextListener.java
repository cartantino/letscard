package com.letscard.struts2.listener;

import com.letscard.jpa.dao.EntityManagerSingleton;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebappServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        EntityManagerSingleton.close();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /*Helper.dropDatabase();
        Helper.populateDatabase(2);*/
    }
}
