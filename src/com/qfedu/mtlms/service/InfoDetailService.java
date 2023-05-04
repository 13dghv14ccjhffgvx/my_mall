package com.qfedu.mtlms.service;

import com.qfedu.mtlms.dao.InfoDetailDAO;
import com.qfedu.mtlms.dto.InfoDetail;

import java.util.List;

/**
 * @Description 完成与评估选项相关的业务操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class InfoDetailService {

    private InfoDetailDAO infoDetailDAO = new InfoDetailDAO();

    /**
     * 根据类目ID查询类目中的选项
     * @param basicInfoId
     * @return
     */
    public List<InfoDetail> listInfoDetailByBasicInfo(int basicInfoId){
        return infoDetailDAO.selectInfoDetailsByBasicInfo(basicInfoId);
    }

    /**
     * 保存评估选项
     * @param infoDetail
     * @param basicInfoId
     * @return
     */
    public boolean saveInfoDetail(InfoDetail infoDetail,int basicInfoId){
        int i = infoDetailDAO.insertInfoDetail(infoDetail, basicInfoId);
        return i>0;
    }

}
