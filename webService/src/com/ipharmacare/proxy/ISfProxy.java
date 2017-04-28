package com.ipharmacare.proxy;

import com.ipharmacare.bean.AuditData;

/**
 * Created by zxiaocun on 2017/4/28.
 */
public interface ISfProxy {
    /**
     * 代理审方接口，将web service转为http接口
     * @param auditData
     * @return
     */
    String doubleSign(AuditData auditData);

    /**
     * 代理双签名接口，将web service转为http接口
     * @param auditData
     * @return
     */
    String auditCenter(AuditData auditData);

    /**
     * 代理轮寻审方结果接口，将web service转为http接口
     * @return
     */
    String obtainAuditResult();
}
