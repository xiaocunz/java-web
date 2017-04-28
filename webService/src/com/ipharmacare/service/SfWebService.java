package com.ipharmacare.service;

import com.ipharmacare.ReadConfig;
import com.ipharmacare.bean.AuditData;
import com.ipharmacare.proxy.SfProxyService;
import com.ipharmacare.util.HttpUtils;
import org.apache.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by zxiaocun on 2017/2/17.
 */
@WebService
public class SfWebService {
  private Logger logger = Logger.getLogger(this.getClass().getName());

  /**
   * 审方
   * @param auditData
   * @return
   */
  @WebMethod
  public String auditCenter(AuditData auditData) {
    logger.info("enter auditCenter......");
    logger.info(auditData);

    String result = SfProxyService.auditCenter(auditData);

    logger.info("result:" + result);
    return result;
  }

  /**
   * 双签名
   * @param auditData
   * @return
   */
  @WebMethod
  public String doubleSign(AuditData auditData) {
    logger.info("enter doubleSign......");
    logger.info(auditData);

    String result = SfProxyService.doubleSign(auditData);

    logger.info("result:" + result);
    return result;
  }

  @WebMethod
  public String obtainAuditResult() {
    logger.info("enter obtainAuditResult......");

    String result = SfProxyService.obtainAuditResult();

    logger.info("result:" + result);
    return result;
  }


  public static void main(String[] args) {
    SfWebService obj = new SfWebService();
    AuditData data = new AuditData();
    data.setXml("");
    data.setCharset("GB2312");
    obj.auditCenter(data);
//    obj.obtainAuditResult();
  }

}
