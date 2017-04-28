package com.ipharmacare.util;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zxiaocun on 2017/3/9.
 */
public class HttpUtils {
    private static Logger logger = Logger.getLogger(HttpUtils.class);

    private static final String HTTP_POST_METHOD = "POST";
    private static final String HTTP_GET_METHOD = "GET";
    private static final String DEFAULT_CHARSET_UTF8 = "UTF-8";
    /**
     * 发送http post请求
     * @param serviceUrl 服务地址
     * @param data 数据
     * @return
     */
    public static String post(String serviceUrl, String data, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = DEFAULT_CHARSET_UTF8;
        }
        String result = "";
        URL dataUrl;
        HttpURLConnection httpCon = null;
        InputStream ins = null;
        OutputStream oputs = null;
        try {
            //构建资源对象
            dataUrl = new URL(serviceUrl);
            //打开连接
            httpCon = (HttpURLConnection) dataUrl.openConnection();
            httpCon.setRequestMethod(HTTP_POST_METHOD);
            httpCon.setRequestProperty("Content-Type", "text/plain; charset=" + charset);
            // 设置往连接中读、写数据
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            //构建输出流，传递xml处方参数
            oputs = httpCon.getOutputStream();
            oputs.write(data.getBytes(charset));
            oputs.flush();
            oputs.close();
            //构建收入流，获取xml警示信息结果
            ins = httpCon.getInputStream();
            byte d[] = new byte[ins.available()];
            ins.read(d);
            result = new String(d,DEFAULT_CHARSET_UTF8);
            ins.close();
            //关闭连接
            httpCon.disconnect();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "发生异常" + e.getMessage();
        } finally {
            StreamUtils.closeSteam(oputs);
            StreamUtils.closeSteam(ins);
            if (null != httpCon) {
                httpCon.disconnect();
            }
        }
        return result;
    }

    /**
     * 发送http get 请求，默认采用UTF-8
     * @param serviceUrl 服务地址
     * @return
     */
    public static String get(String serviceUrl) {
        String result = "";
        URL dataUrl;
        HttpURLConnection httpCon = null;
        InputStream ins = null;
        try {
            //构建资源对象
            dataUrl = new URL(serviceUrl);
            //打开连接
            httpCon = (HttpURLConnection) dataUrl.openConnection();
            httpCon.setRequestMethod(HTTP_GET_METHOD);
            // 设置往连接中读数据
            httpCon.setDoInput(true);
            //构建输入流，获取返回结果
            ins = httpCon.getInputStream();
            byte d[] = new byte[ins.available()];
            ins.read(d);
            result = new String(d,DEFAULT_CHARSET_UTF8);
            ins.close();
            //关闭连接
            httpCon.disconnect();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return "发生异常" + e.getMessage();
        } finally {
            StreamUtils.closeSteam(ins);
            if (null != httpCon) {
                httpCon.disconnect();
            }
        }
        return result;
    }
}
