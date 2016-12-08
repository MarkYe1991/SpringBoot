package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Created by edmund.wang on 8/8/2016.
 */
public class PropertiesUtil {

    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    private static Properties properties = new Properties();

    private static Properties jndiProperties = new Properties();

    private PropertiesUtil() {
    }

    public static Properties getCommonProperties(){
        if(properties == null || properties.size() == 0){
            properties = getResourceProperties("common", properties);
        }
        return properties;
    }

    public static Properties getJndiProperties(){
        if(jndiProperties == null || jndiProperties.size() == 0){
            jndiProperties = getResourceProperties("jndi", jndiProperties);
        }
        return jndiProperties;
    }

    private static Properties getResourceProperties(String propertyName, Properties properties){
        InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/" + propertyName+".properties");
        try {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            logger.error("Failed to get ors properties.");
            return null;
        }
    }
}
