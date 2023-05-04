package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.BasicInfoDAO;
import com.qfedu.mtlms.dto.BasicInfo;

import java.util.List;

/**
 * @Description 完成评估类目相关业务
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BasicInfoService {

    BasicInfoDAO basicInfoDAO = new BasicInfoDAO();

    /**
     * 评估类目列表
     * @return
     */
    public List<BasicInfo> listBasicInfos(){
        return basicInfoDAO.selectBasicInfos();
    }

    /**
     * 保存类目信息
     * @param basicInfo
     * @return
     */
    public boolean saveBasicInfo(BasicInfo basicInfo) {
        int i = basicInfoDAO.insertBasicInfo(basicInfo);
        return i>0;
    }
}
