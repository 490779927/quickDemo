package com.example.demo.base.mybatis;

import lombok.Data;

/**
 * 公共对象
 * @author ：yangjin.
 * @Date ：Created in 21:10 2019/11/27
 */
@Data
public class DataBaseInfo {
    private String url = "jdbc:mysql://127.0.0.1:3306/";
    /**
     *库名
     */
    private String dataBaseName;
    private String driverName = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "123456";


    public DataBaseInfo() {
        this.url = url;
        this.dataBaseName = dataBaseName;
        this.driverName = driverName;
        this.username = username;
        this.password = password;
    }
    public String getUrl(){
        return  url+dataBaseName+"?useUnicode=true&characterEncoding=utf8&useSSL=false";
    }

    private static class SingleTonHolerDataBaseInfo{
        private static DataBaseInfo INSTANCE = new DataBaseInfo();
    }

    public static DataBaseInfo getInstance(){
        return SingleTonHolerDataBaseInfo.INSTANCE;
    }
}
