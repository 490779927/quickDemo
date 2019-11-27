package com.example.demo.base.mybatis;

import lombok.Data;

/**
 * @author ：yangjin.
 * @Date ：Created in 21:06 2019/11/27
 */
@Data
public class PathInfo {
    /**
     *表名
     */
    private String tableName;
    /**
     *父目录
     */
    private String commPath = "com.example.demo";
    /**
     *作者
     */
    private String author = "yangjin";
    /**
     *公共domain路径
     */
    private String baseEntityPath = "";
    /**
     *公共controller路径
     */
    private String baseControllerPath = "";
    /**
     *是否全库生成
     */
    private boolean isGeneratorAll = false;

    private boolean entity = true;

    private boolean controller= true;

    private boolean mapper= true;

    private boolean service= true;

    private boolean xml= true;

    private boolean serviceImpl= true;

    private boolean fileOverride = true;

    public PathInfo() {
        this.tableName = tableName;
        this.commPath = commPath;
        this.author = author;
        this.baseEntityPath = baseEntityPath;
        this.baseControllerPath = baseControllerPath;
    }
    private static class SingleTonHolerPathInfo{
        private static PathInfo INSTANCE = new PathInfo();
    }

    public static PathInfo getInstance(){
        return SingleTonHolerPathInfo.INSTANCE;
    }
}
