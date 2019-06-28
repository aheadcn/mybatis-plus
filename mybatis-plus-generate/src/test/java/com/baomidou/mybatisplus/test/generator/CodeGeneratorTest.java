package com.baomidou.mybatisplus.test.generator;

import org.junit.Test;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * 代码生成器 示例
 * </p>
 *
 * @author K神
 * @date 2017/12/29
 */
public class CodeGeneratorTest {

    /**
     * 是否强制带上注解
     */
    boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * 默认是以I开头
     * user表 -> IUserService, UserServiceImpl
     */
    boolean serviceClassNameStartWithI = true;

    String tablePrefix = "";
    String moduleName = "system";

    @Test
    public void generateCode() {
        String packageName = "com.talkwed";
        enableTableFieldAnnotation = false;
        tableIdType = IdType.INPUT;
        tablePrefix = "t_sys_";
        moduleName =  "system";
        generateByTables(packageName + ".merchant", "t_sys_user");
    }

    private void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/talkwed_merchant?characterEncoding=utf8&serverTimezone=UTC";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
            .setUrl(dbUrl)
            .setUsername("root")
            .setPassword("123456")
            .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//            .setCapitalMode(true)
//            .setEntityLombokModel(false)
//            .setDbColumnUnderline(true)
//            .setNaming(NamingStrategy.underline_to_camel)
//            .entityTableFieldAnnotationEnable(enableTableFieldAnnotation)
//            .fieldPrefix(fieldPrefix)//test_id -> id, test_type -> type
//            .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        // 此处可以修改为您的表前缀
        strategyConfig.setTablePrefix(tablePrefix);
        //限制只生某个表的业务
        strategyConfig.setInclude(tableNames);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);

        config.setActiveRecord(false)
            .setIdType(tableIdType)
            .setAuthor("chenjie")
            .setOutputDir("d:\\tmp\\codeGen")
            .setControllerOutputDir("d:\\tmp\\codeGen\\controller")
            .setInterfaceOutputDir("d:\\tmp\\codeGen\\interface")
            .setFileOverride(true)
            .setEnableCache(false)
            .setBaseColumnList(true)
            .setBaseResultMap(true)
            .setOpen(true);



        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName);
//        packageConfig.setController("controller");
//        packageConfig.setEntity("entity");

        String path = "modular." + moduleName;

        packageConfig.setEntity(path + ".entity");
        packageConfig.setMapper(path + ".dao");
        packageConfig.setXml(path + ".dao.mapping");
        packageConfig.setService(path + ".service");
        packageConfig.setServiceImpl(path + ".service.impl");
        packageConfig.setController(path + ".controller");


        new AutoGenerator().setGlobalConfig(config)
            .setDataSource(dataSourceConfig)
            .setStrategy(strategyConfig)
            .setPackageInfo(
                packageConfig
            ).execute();
    }
}
