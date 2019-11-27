package com.example.demo.base.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 公共对象
 *
 * @author ：yangjin.
 * @Date ：Created in 21:09 2019/11/27
 */
@Data
public class MybatisPlusGeneratorComm {
    public static void setCommParams(PathInfo pathInfo, DataBaseInfo dataBaseInfo) {
        //代码生成器
        AutoGenerator mpg = getAutoGenerator(pathInfo);

        /*-------------------- 数据源配置 --------------------*/
        //数据源配置，通过该配置，指定需要生成代码的具体数据库
        DataSourceConfig dsc = getDataSourceConfig(dataBaseInfo, mpg);

        /*-------------------- 包配置 --------------------*/
        //包名配置，通过该配置，指定生成代码的包路径
        PackageConfig packageConfig = getPackageConfig(pathInfo, mpg);

        /*-------------------- 策略配置 --------------------*/
        StrategyConfig strategy = getStrategyConfig(pathInfo, dsc);
        //为代码生成器设置表策略
        mpg.setStrategy(strategy);
        //为代码生成器设置模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static AutoGenerator getAutoGenerator(PathInfo pathInfo) {
        AutoGenerator mpg = new AutoGenerator();
        //全局策略配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        if (pathInfo.isXml()) {
            InjectionConfig cfg = getInjectionConfig(pathInfo, projectPath);
            mpg.setCfg(cfg);
        }
        //生成文件输出目录
        gc.setOutputDir(String.format("%s/src/main/java", projectPath));
        //创建人
        gc.setAuthor(pathInfo.getAuthor());
        //是否打开输出目录，默认值true
        gc.setOpen(false);
        //开启 BaseResultMap，默認false
        gc.setBaseResultMap(true);
        /**
         * 开启 ActiveRecord 模式
         */
        gc.setActiveRecord(true);
        //开启 通用查询结果列，默認false
        gc.setBaseColumnList(true);
        //设置主键类型
        gc.setIdType(IdType.AUTO);
        //开启Swagger2
        gc.setSwagger2(true);
        // 覆盖生成 默认false
        gc.setFileOverride(pathInfo.isFileOverride());
        //为代码生成器设置全局配置
        mpg.setGlobalConfig(gc);
        return mpg;
    }

    private static InjectionConfig getInjectionConfig(PathInfo pathInfo, String projectPath) {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig("/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String outPath = String.format("%s/src/main", projectPath);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return outPath + "/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static StrategyConfig getStrategyConfig(PathInfo pathInfo, DataSourceConfig dsc) {
        //数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        StrategyConfig strategy = new StrategyConfig();
        //数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自定义继承的Entity类全称，带包名
        if (org.apache.commons.lang3.StringUtils.isNotBlank(pathInfo.getBaseEntityPath())) {
            strategy.setSuperEntityClass(pathInfo.getBaseEntityPath());
        }
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        //自定义继承的Controller类全称，带包名
        if (org.apache.commons.lang3.StringUtils.isNotBlank(pathInfo.getBaseControllerPath())) {
            strategy.setSuperControllerClass(pathInfo.getBaseControllerPath());
        }
        //需要包含的表名，允许正则表达式（与exclude二选一配置 默认生成单个表 可设置isGeneratorAll 为true 生成整个库
        if (pathInfo.isGeneratorAll()) {
            strategy.setInclude(getTableNames(dsc));
        } else {
            strategy.setInclude(pathInfo.getTableName());
        }
        //自定义基础的Entity类，公共字段
        if (org.apache.commons.lang3.StringUtils.isNotBlank(pathInfo.getBaseEntityPath())) {
            strategy.setSuperEntityColumns("id");
        }
        //驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        //逻辑删除属性名称
        strategy.setLogicDeleteFieldName("is_deleted");
        //是否生成实体时，生成字段注解
        strategy.entityTableFieldAnnotationEnable(true);
        //设置表填充字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("creator_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        strategy.setTableFillList(tableFillList);
        //【实体】是否为构建者模型（默认 false）
        strategy.setEntityBuilderModel(true);
        //【实体】是否生成字段常量（默认 false）
        strategy.setEntityColumnConstant(true);
        return strategy;
    }

    private static PackageConfig getPackageConfig(PathInfo pathInfo, AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();

        pc.setParent(pathInfo.getCommPath());
        pc.setEntity("model.domain");
        pc.setMapper("mapper");
        pc.setController("web.frontend");
        TemplateConfig templateConfig = new TemplateConfig();
        if (!pathInfo.isEntity()) {
            templateConfig.setEntity(null);
        }
        if (!pathInfo.isController()) {
            templateConfig.setController(null);
        }
        if (!pathInfo.isMapper()) {
            templateConfig.setMapper(null);
        }
        if (!pathInfo.isService()) {
            templateConfig.setService(null);
        }
        if (!pathInfo.isServiceImpl()) {
            templateConfig.setServiceImpl(null);
        }
        templateConfig.setXml(null);
        mpg.setPackageInfo(pc);
        mpg.setTemplate(templateConfig);
        return pc;
    }

    private static DataSourceConfig getDataSourceConfig(DataBaseInfo dataBaseInfo, AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataBaseInfo.getUrl());
        dsc.setDriverName(dataBaseInfo.getDriverName());
        dsc.setUsername(dataBaseInfo.getUsername());
        dsc.setPassword(dataBaseInfo.getPassword());
        mpg.setDataSource(dsc);
        return dsc;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(PathInfo pathInfo) {
        return getString(pathInfo);
    }

    static String getString(PathInfo pathInfo) {

        String ipt = scanner(String.format("生成范围 y=整个库，n=单表： \n1 -> %s\n2 -> %s",
                "y", "n"));
        if (ipt.equals("y")) {
            pathInfo.setGeneratorAll(true);
            return "";
        } else if (ipt.equals("n")) {
            return scanner("表名");
        } else {
            throw new MybatisPlusException("请输入正确的" + ipt + "！");
        }
    }


    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip);
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static String[] getTableNames(DataSourceConfig dsc) {
        StringBuffer sbTables = new StringBuffer();
        try {
            DatabaseMetaData metaData = dsc.getConn().getMetaData();
            ResultSet rs = metaData.getTables(null, null, null, new String[]{"TABLE"});
            while (rs.next()) {
                sbTables.append(rs.getString("TABLE_NAME"));
                sbTables.append(",");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sbTables.toString().split(",");
    }
}
