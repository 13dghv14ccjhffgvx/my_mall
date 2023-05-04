package com.qfedu.mtlms.dto;

import java.util.Date;

/**
 * @Description 品牌信息实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class Brand {

    private int brandId;
    private String brandName;
    private String brandLogo;
    private String brandDesc;
    private Date createTime;
    private int brandStatus;

    public Brand() {
    }

    public Brand(int brandId, String brandName, String brandLogo, String brandDesc, Date createTime, int brandStatus) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandLogo = brandLogo;
        this.brandDesc = brandDesc;
        this.createTime = createTime;
        this.brandStatus = brandStatus;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getBrandStatus() {
        return brandStatus;
    }

    public void setBrandStatus(int brandStatus) {
        this.brandStatus = brandStatus;
    }
}
