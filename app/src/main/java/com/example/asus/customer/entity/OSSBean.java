package com.example.asus.customer.entity;

/**
 * Created by shuai on 2018/10/15.
 */

public class OSSBean {

    /**
     * StatusCode : 200
     * AccessKeyId : STS.NHbE2t9DKipQuik8oZQ4j78Vg
     * AccessKeySecret : CjVzbjKkkTFXQcYJGjrGLyUdXPyujYqwSzgUfKeZg2Qm
     * SecurityToken : CAIShwJ1q6Ft5B2yfSjIr4vXDojA1Jtq3rK6d0/a3G8PXbtG2P39hTz2IHFOfnRtAeoesvQymm9Z6/wblqB6T55OSAmcNZIoRBH9Q4PlMeT7oMWQweEuuv/MQBquaXPS2MvVfJ+OLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/m6/Ngdc9FHHP7D1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca3WgZgGhku6Ok2Z/euFiMzn+akbdO+duhf8OeApMybMslYbCcx/drc6fN6ilU5iVR+b1+5K4+omad5IzAWwMJv0XbbbWFr4c2NnxwYqkrBqhDt+Pgkv51vOPekYntwgpKJ/tSVynPwSh1phqAATRMUh2Lx/mJ5f3nBna2qqaLi/iMh3VrDUU2GrcytyQhJGJ99vn8yMQ5xCxQnK72FDbYjfX6s2X+vfjMElRrdhwZ1kWjoS9a2OLXyiuxU0d+wJEAnYdSPS+nXoMmn22r9j7hGGewfcFbt7PF2QQ4ESQln1FTJIuTysjxn4DYkAEx
     * Expiration : 2018-10-19T07:53:34Z
     */

    private String StatusCode;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String SecurityToken;
    private String Expiration;

    public String getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(String StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String AccessKeyId) {
        this.AccessKeyId = AccessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String SecurityToken) {
        this.SecurityToken = SecurityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String Expiration) {
        this.Expiration = Expiration;
    }
}
