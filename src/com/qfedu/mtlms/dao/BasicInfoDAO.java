package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.BasicInfo;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 类目数据库访问操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class BasicInfoDAO {

    /**
     * 查询所有的评估类目的列表
     * @return
     */
    public List<BasicInfo> selectBasicInfos(){
        List<BasicInfo> basicInfoList = new ArrayList<>();
        try {
            String sql = "select basic_info_id basicInfoId,basic_info_name basicInfoName,basic_info_status basicInfoStatus from tb_basic_info";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            basicInfoList = queryRunner.query(sql,new BeanListHandler<BasicInfo>(BasicInfo.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return basicInfoList;
    }

    /**
     * 添加类目信息到数据库
     * @param basicInfo
     * @return
     */
    public int insertBasicInfo(BasicInfo basicInfo) {
        int i = 0;
        try {
            String sql = "insert into tb_basic_info(basic_info_name,basic_info_status) values(?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,basicInfo.getBasicInfoName(),basicInfo.getBasicInfoStatus());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
