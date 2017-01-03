package com.base.java.util.mysql;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;
import java.util.Set;

public class SessionCreator {
    private String url;
    private String username;
    private Set<Class<?>> modelClass;
    private DataBaseConfig dataBaseConfig;

    /**
     * DB Operation:
     * 1,estliablish connection
     * 2,load driver
     * 3,execute sql
     * 4,close connection
     *
     * @return
     */
    SessionFactory create() {
        Properties properties = new Properties();
        //create Connection
        properties.setProperty(Environment.DIALECT, "org.hibernate.dialict.MySQLDialect");
        properties.setProperty(Environment.DRIVER, dataBaseConfig.getDriver());
        properties.setProperty(Environment.URL, String.format("jdbc:%s?useUnicode=true&characterEncoding=utf8",dataBaseConfig.getUrl()));
        properties.setProperty(Environment.USER, dataBaseConfig.getUsername());
        properties.setProperty(Environment.PASS, dataBaseConfig.getPassword());

        //pool
        properties.setProperty(Environment.CONNECTION_PROVIDER, "org.hibernate.service.jdbc.connections.internal.C3P0ConnectionProvider");
        properties.setProperty(Environment.C3P0_MAX_SIZE, String.valueOf(dataBaseConfig.getMaxSize()));
        properties.setProperty(Environment.C3P0_MIN_SIZE, "3");
        properties.setProperty(Environment.C3P0_MAX_STATEMENTS, "100");
        properties.setProperty(Environment.C3P0_IDLE_TEST_PERIOD, "60");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(properties);
        ServiceRegistry registry = builder.build();

        Configuration configuration = new Configuration();
        modelClass.forEach(configuration::addAnnotatedClass);

        return configuration.buildSessionFactory(registry);
    }
}
