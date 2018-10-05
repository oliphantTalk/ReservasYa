package com.reservasYa;

import com.reservasYa.models.User;
import com.reservasYa.models.dao.UserDAOImpl;
import com.reservasYa.repository.GenericHibernateDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Hello world!
 *
 */
public class App
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {



        LOGGER.info("[main] Starting application ...");

        DispatcherServlet dispatcherServlet = new DispatcherServlet();//dispatcher de controllers
        dispatcherServlet.setContextClass(XmlWebApplicationContext.class);
        dispatcherServlet.setContextConfigLocation("classpath:application-context.xml");

        WebAppContext handler = new WebAppContext();
        handler.addServlet(new ServletHolder(dispatcherServlet), "/");
        handler.setResourceBase("src/main/resources");


        Server server = new Server(8080);

        server.setHandler(handler);

        server.start();
        LOGGER.info("[main] Application started...");

        server.join();
        LOGGER.info("[main] Application was shutdown!");


        GenericHibernateDao<User> genericHibernateDao = new GenericHibernateDao<>();
        User user = new User("nano", "barrena", "n@n", "wefw");
        UserDAOImpl userDao = new UserDAOImpl();
        userDao.setDao(genericHibernateDao);
        userDao.getDao().create(user);
        LOGGER.info("asdasd" + userDao.getDao().findAll().toString());





    }
}
