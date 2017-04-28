package com.ipharmacare.proxy;

import com.ipharmacare.ReadConfig;
import com.ipharmacare.bean.AuditData;
import com.ipharmacare.util.HttpUtils;
import org.apache.log4j.Logger;

/**
 * Created by zxiaocun on 2017/4/28.
 */
public class SfProxyService{
    private static Logger logger = Logger.getLogger(SfProxyService.class.getName());

    /**
     * 数据推送地址
     */
    private static final String SF_AUDIT_CENTER_PATH = "/api/v1/auditcenter";
    /**
     * 双签名地址
     */
    private static final String SF_DOUBLE_SIGN_PATH = "/api/v1/doublesign";
    /**
     * 轮询获取结果地址
     */
    private static final String SF_RESULT_RECEIVE_PATH = "/api/v1/auditresult";
    /**
     * 默认字符集
     */
    private static final String CHARSET_UTF8_QUERY_STRING = "charset=";
    private static final String VALIDATE_XML = "1";
    private static final int TRUE = 1;
    private static final int FALSE = 0;
    private static final String SF_SERVICE_URL = "sf-service-url";

    public static String doubleSign(AuditData auditData) {
        if (null == auditData) {
            logger.error("auditData is null.");
            return null;
        }
        String charset = auditData.getCharset();
        // 请求路径
        String path = "";
        // 请求服务地址
        String url = "";
        // 返回结果
        String result = "";

        int validate = VALIDATE_XML.equals(auditData.getValidate()) ? TRUE : FALSE;
        StringBuilder sb = new StringBuilder(SF_DOUBLE_SIGN_PATH);
        path = sb.append("?validateXml=").append(validate).append("&").append(CHARSET_UTF8_QUERY_STRING).append(charset).toString();
        url = getRequestUrl(path);

        result = HttpUtils.post(url, auditData.getXml(), charset);
        logger.info(result);

        return result;
    }

    public static String auditCenter(AuditData auditData) {
        if (null == auditData) {
            logger.error("auditData is null.");
            return "";
        }
        String charset = auditData.getCharset();
        // 请求路径
        String path = "";
        // 请求服务地址
        String url = "";
        // 返回结果
        String result = "";

        int validate = VALIDATE_XML.equals(auditData.getValidate()) ? TRUE : FALSE;
        StringBuilder sb = new StringBuilder(SF_AUDIT_CENTER_PATH);
        path = sb.append("?validateXml=").append(validate).append("&").append(CHARSET_UTF8_QUERY_STRING).append(charset).toString();
        url = getRequestUrl(path);

        result = HttpUtils.post(url, auditData.getXml(), charset);
        logger.info(result);

        return result;
    }

    public static String obtainAuditResult() {
        // 请求路径
        String path = "";
        // 请求服务地址
        String url = "";
        // 返回结果
        String result = "";

        StringBuilder sb = new StringBuilder(SF_RESULT_RECEIVE_PATH);
        url = getRequestUrl(path);

        result = HttpUtils.get(url);
        logger.info(result);

        return result;
    }

    private static String getRequestUrl(String path) {
        ReadConfig rcfg = ReadConfig.getIntance();
        String address_port = rcfg.getPValue(SF_SERVICE_URL);
        StringBuilder sb = new StringBuilder(address_port);
        String url = "";
        url = sb.append(path).toString();
        return url;
    }
}
