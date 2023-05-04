package com.qfedu.mtlms.dto;

import java.util.List;

/**
 * @Description 评估类目信息实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BasicInfo {

    private int basicInfoId;
    private String basicInfoName;
    private int basicInfoStatus;
    //定义infoDetailList属性，存放当前类目下的选项
    private List<InfoDetail> infoDetailList;

    public List<InfoDetail> getInfoDetailList() {
        return infoDetailList;
    }

    public void setInfoDetailList(List<InfoDetail> infoDetailList) {
        this.infoDetailList = infoDetailList;
    }

    public BasicInfo() {
    }

    public BasicInfo(int basicInfoId, String basicInfoName, int basicInfoStatus) {
        this.basicInfoId = basicInfoId;
        this.basicInfoName = basicInfoName;
        this.basicInfoStatus = basicInfoStatus;
    }

    public int getBasicInfoId() {
        return basicInfoId;
    }

    public void setBasicInfoId(int basicInfoId) {
        this.basicInfoId = basicInfoId;
    }

    public String getBasicInfoName() {
        return basicInfoName;
    }

    public void setBasicInfoName(String basicInfoName) {
        this.basicInfoName = basicInfoName;
    }

    public int getBasicInfoStatus() {
        return basicInfoStatus;
    }

    public void setBasicInfoStatus(int basicInfoStatus) {
        this.basicInfoStatus = basicInfoStatus;
    }
}
