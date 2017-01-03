package com.base.java.util.config;

import com.base.java.util.exception.ConfigException;
import com.base.java.util.reflect.ClassLoaderUtil;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigLoader implements Config {
    private static final String CONF_PATH = "/config";
    private static String CONFIG_FILES = "config.properties";
    private Properties properties;

    public ConfigLoader() {
        this.properties = new Properties();

        String confPath = null;
        URL url = ClassLoaderUtil.getResourceUrl(CONFIG_FILES);
        confPath = url.getPath();
        if (confPath == null) {
            confPath = System.getenv("CONF_PATH");
        }

        if (confPath == null) {
            confPath = CONF_PATH;
        }

        String path = confPath + "/" + CONFIG_FILES;
        try {
            FileInputStream stream = new FileInputStream(confPath);
            this.properties.load(stream);
            stream.close();
        } catch (Exception e) {
            String message = String.format("unable to load config file [%s].", path);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getStringValue(String propertyName) {
        return getProperty(propertyName);
    }

    @Override
    public Integer getIntegerValue(String propertyName) {
        return Integer.parseInt(getProperty(propertyName));
    }

    private String getProperty(String propertyName) {
        String result = properties.getProperty(propertyName);
        if (null == result) {
            Exception exception = (new ConfigException(String.format("unable to get config [%s]", propertyName)));
            return "";
        }
        return String.valueOf(result).trim();
    }
}
