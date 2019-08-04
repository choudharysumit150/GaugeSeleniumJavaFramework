package hf.common.utils;

import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 * Created by Sumit Choudhary on 6/14/2019
 */
public class PropertiesHelper {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);
    private static Properties configProperties = null;
    private PropertiesHelper(){

    }

    private static Properties getInstance(){
        if(configProperties==null){
            logger.info("Load testconfig.properties file");
            try {
                URL configURL = Resources.getResource("testconfig.properties");
                InputStream inputStream = Resources.asByteSource(configURL).openBufferedStream();
                configProperties = new Properties();
                configProperties.load(inputStream);

            } catch (IOException e) {
                logger.error("Error in loading testconfig.properties file");
                e.printStackTrace();
            }catch (Exception ex){
                logger.error("Error in loading properties file");
                ex.printStackTrace();
            }
        }

        return configProperties;
    }

    public static String getPropertyValue(String key){
        String value = null;
        try {
            value = PropertiesHelper.getInstance().getProperty(key);
            logger.debug("Key: " + key + " Property value: " + value);
        }catch (Exception ex){
            logger.error("Error in reading properties file");
            ex.printStackTrace();
        }finally {
            return value;
        }
    }



}
