package com.example.demo.base;

import com.example.demo.base.mybatis.DataBaseInfo;
import com.example.demo.base.mybatis.MybatisPlusGeneratorComm;
import com.example.demo.base.mybatis.PathInfo;

/**
 * mybatis生成
 * @author ：yangjin.
 * @Date ：Created in 21:04 2019/11/27
 */
public class MybatisGenerate {
    public static void main(String[] args) {
        PathInfo pathInfo = PathInfo.getInstance();
        pathInfo.setTableName(MybatisPlusGeneratorComm.scanner(pathInfo));
        pathInfo.setBaseEntityPath("com.example.demo.base.dto.BaseEntity");
        pathInfo.setBaseControllerPath("com.example.demo.base.interceptor.BaseController");
        pathInfo.setFileOverride(true);
        DataBaseInfo dataBaseInfo = DataBaseInfo.getInstance();
        dataBaseInfo.setDataBaseName("wx");
        MybatisPlusGeneratorComm.setCommParams(pathInfo,dataBaseInfo);
    }
}
