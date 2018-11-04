package com.dy.bom.code.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author:nicky_x
 * Description:配置
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Component
public class GenConf extends BaseEntity {
    @Value("${output.dir}")
    private String outputDir;
    @Value("${table.include}")
    private String tableInclude;
    @Value("${table.ignored}")
    private String tableIgnored;
    @Value("${base.db.bean.package}")
    private String baseDbBeanPackage;
    @Value("${bean.mapper.package}")
    private String beanMapperPackage;
    @Value("${bean.service.package}")
    private String beanServicePackage;
    @Value("${bean.controller.package}")
    private String beanControllerPackage;
    @Value("${schema}")
    private String schema;
    @Value(("${bean.base.mapper.package}"))
    private String beanBaseMapperPackage;
    @Value("${bean.base.service.package}")
    private String beanBaseServicePackage;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getTableInclude() {
        return tableInclude;
    }

    public void setTableInclude(String tableInclude) {
        this.tableInclude = tableInclude;
    }

    public String getTableIgnored() {
        return tableIgnored;
    }

    public void setTableIgnored(String tableIgnored) {
        this.tableIgnored = tableIgnored;
    }

    public String getBaseDbBeanPackage() {
        return baseDbBeanPackage;
    }

    public void setBaseDbBeanPackage(String baseDbBeanPackage) {
        this.baseDbBeanPackage = baseDbBeanPackage;
    }

    public String getBeanMapperPackage() {
        return beanMapperPackage;
    }

    public void setBeanMapperPackage(String beanMapperPackage) {
        this.beanMapperPackage = beanMapperPackage;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getBeanServicePackage() {
        return beanServicePackage;
    }

    public void setBeanServicePackage(String beanServicePackage) {
        this.beanServicePackage = beanServicePackage;
    }

    public String getBeanBaseMapperPackage() {
        return beanBaseMapperPackage;
    }

    public void setBeanBaseMapperPackage(String beanBaseMapperPackage) {
        this.beanBaseMapperPackage = beanBaseMapperPackage;
    }

    public String getBeanBaseServicePackage() {
        return beanBaseServicePackage;
    }

    public void setBeanBaseServicePackage(String beanBaseServicePackage) {
        this.beanBaseServicePackage = beanBaseServicePackage;
    }

    public String getBeanControllerPackage() {
        return beanControllerPackage;
    }

    public void setBeanControllerPackage(String beanControllerPackage) {
        this.beanControllerPackage = beanControllerPackage;
    }
}
