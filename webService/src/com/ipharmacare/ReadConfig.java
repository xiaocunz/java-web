package com.ipharmacare;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zxiaocun on 2017/2/18.
 */
public class ReadConfig {
    private static ReadConfig config = null;
    private String porpertiesFile = "sf.properties";
    private Properties properties;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static Lock lock = new ReentrantLock();

    /**
     * 获取当前类的实例
     * @return
     */
    public static ReadConfig getIntance(){
        if (null != config) {
            return config;
        }
        synchronized (lock) {
            config = new ReadConfig();
        }
        return config;
    }

    private ReadConfig(){
        init();
    }

    /**
     *  初始化配置
     */
    private void init(){
        InputStream in = this.getClass().getResourceAsStream(porpertiesFile);
        properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 获取属性值
     * @param key
     * @return
     */
    public String getPValue(String key) {
        String pValue = properties.getProperty(key);
        return pValue;
    }
}
