package com.qfedu.mtlms.dto;

/**
 * @Description 评估选项实体类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class InfoDetail {

    private int infoDetailId;
    private String infoDetailName;
    private String infoDetailDesc;
    //评估选项所属的评估类目ID
    //private int basicInfoId;

    public InfoDetail() {
    }

    public InfoDetail(int infoDetailId, String infoDetailName, String infoDetailDesc) {
        this.infoDetailId = infoDetailId;
        this.infoDetailName = infoDetailName;
        this.infoDetailDesc = infoDetailDesc;
    }

    public int getInfoDetailId() {
        return infoDetailId;
    }

    public void setInfoDetailId(int infoDetailId) {
        this.infoDetailId = infoDetailId;
    }

    public String getInfoDetailName() {
        return infoDetailName;
    }

    public void setInfoDetailName(String infoDetailName) {
        this.infoDetailName = infoDetailName;
    }

    public String getInfoDetailDesc() {
        return infoDetailDesc;
    }

    public void setInfoDetailDesc(String infoDetailDesc) {
        this.infoDetailDesc = infoDetailDesc;
    }
}
