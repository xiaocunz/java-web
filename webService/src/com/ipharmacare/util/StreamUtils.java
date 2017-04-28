package com.ipharmacare.util;

import com.ipharmacare.proxy.SfProxyService;
import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by zxiaocun on 2017/4/28.
 */
public class StreamUtils {
    private static Logger logger = Logger.getLogger(StreamUtils.class.getName());
    /**
     * 流关闭
     *
     * @param c Closeable的子类
     */
    public static void closeSteam(Closeable c)
    {
        try
        {
            if (null != c)
            {
                c.close();
                c = null;
            }
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
        }
    }
}
