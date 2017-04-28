package com.ipharmacare.bean;

import java.io.Serializable;

/**
 * Created by zxiaocun on 2017/2/17.
 */
public class AuditData implements Serializable{
    private static final long serialVersionUID = 8460486372404516396L;
    private String xml;
    private String validate;
    private String charset;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    @Override
    public String toString() {
        return "xml:" + this.getXml() + "; validate:" + this.getValidate() + ";charset:" + charset;
    }
}
