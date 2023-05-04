package com.qfedu.mtlms.dao;

import com.qfedu.mtlms.dto.InfoDetail;
import com.qfedu.mtlms.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 关于评估选项的数据库操作
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class InfoDetailDAO {

    /**
     * 根据评估类目ID，查询此评估类目下的所有的评估选项
     * @param basicInfoId
     * @return
     */
    public List<InfoDetail> selectInfoDetailsByBasicInfo(int basicInfoId){
        List<InfoDetail> infoDetailList = new ArrayList<>();
        try {
            String sql = "select info_detail_id infoDetailId,info_detail_name infoDetailName,info_detail_desc infoDetailDesc from tb_info_detail where fk_basic_info_id=?";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            infoDetailList = queryRunner.query(sql,new BeanListHandler<InfoDetail>(InfoDetail.class),basicInfoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoDetailList;
    }

    /**
     * 添加评估选项
     * @param infoDetail  评估选项信息
     * @param basicInfoId 选项所属的评估类目ID
     * @return
     */
    public int insertInfoDetail(InfoDetail infoDetail,int basicInfoId){
        int i = 0;
        try {
            String sql = "insert into tb_info_detail(info_detail_name,info_detail_desc,fk_basic_info_id) values(?,?,?)";
            QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
            i = queryRunner.update(sql,infoDetail.getInfoDetailName(),infoDetail.getInfoDetailDesc(),basicInfoId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

}
