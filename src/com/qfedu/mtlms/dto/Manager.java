package com.qfedu.mtlms.dto;

import java.util.Date;

/**
 * @Description 管理员信息实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class Manager {

    private String mgrId;
    private String loginName;
    private String loginPwd;
    private String mgrName;
    private String mgrGender;
    private String mgrTel;
    private String mgrEmail;
    private String mgrQQ;
    private Date createTime;

    //无参构造器

    public Manager() {
    }

    //全参构造器

    public Manager(String mgrId, String loginName, String loginPwd, String mgrName, String mgrGender, String mgrTel, String mgrEmail, String mgrQQ, Date createTime) {
        this.mgrId = mgrId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.mgrName = mgrName;
        this.mgrGender = mgrGender;
        this.mgrTel = mgrTel;
        this.mgrEmail = mgrEmail;
        this.mgrQQ = mgrQQ;
        this.createTime = createTime;
    }

    //get和set方法

    public String getMgrId() {
        return mgrId;
    }

    public void setMgrId(String mgrId) {
        this.mgrId = mgrId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public String getMgrGender() {
        return mgrGender;
    }

    public void setMgrGender(String mgrGender) {
        this.mgrGender = mgrGender;
    }

    public String getMgrTel() {
        return mgrTel;
    }

    public void setMgrTel(String mgrTel) {
        this.mgrTel = mgrTel;
    }

    public String getMgrEmail() {
        return mgrEmail;
    }

    public void setMgrEmail(String mgrEmail) {
        this.mgrEmail = mgrEmail;
    }

    public String getMgrQQ() {
        return mgrQQ;
    }

    public void setMgrQQ(String mgrQQ) {
        this.mgrQQ = mgrQQ;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    //toString

    @Override
    public String toString() {
        return "Manager{" +
                "mgrId='" + mgrId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", mgrName='" + mgrName + '\'' +
                ", mgrGender='" + mgrGender + '\'' +
                ", mgrTel='" + mgrTel + '\'' +
                ", mgrEmail='" + mgrEmail + '\'' +
                ", mgrQQ='" + mgrQQ + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
