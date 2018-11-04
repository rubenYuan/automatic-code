package com.dy.bom.code.service;

import com.dy.bom.code.util.FreemarkerUtil;
import com.dy.bom.code.util.StringUtil;
import com.dy.bom.code.bean.GenConf;
import com.dy.bom.code.bean.Table;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

/**
 * Author:nicky_x
 * Description:表生成Service
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Service
public class CodeGenService {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenService.class);
    @Resource
    private TableService tableService;
    @Resource
    private GenConf genConf;

    /**
     * 自动生产代码
     *
     * @throws Exception
     */
    public void genCode() throws Exception {
        if (StringUtil.isBlank(genConf.getOutputDir())) {
            logger.error("输出目录没有！！！");
            return;
        }
        File outputDir = new File(genConf.getOutputDir());
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        } else {
            outputDir.delete();
            outputDir.mkdirs();
        }

        File baseDbBeanPackage = new File(genConf.getOutputDir() + "/" + "/bean/");
        File beanMapperPackage = new File(genConf.getOutputDir() + "/mapper/");
        File beanServicePackage = new File(genConf.getOutputDir() + "/service/");
        File beanControllerPackage = new File(genConf.getOutputDir() + "/controller/");
        File beanMapperXmlPackage = new File(genConf.getOutputDir() + "/xml");


        baseDbBeanPackage.mkdirs();
        beanMapperPackage.mkdirs();
        beanServicePackage.mkdirs();
        beanControllerPackage.mkdirs();
        beanMapperXmlPackage.mkdirs();

        List<Table> tableList = tableService.getTableInfoList();
        for (Table table : tableList) {
            logger.info("gen code for table,tableName={}", table.getTableName());
            Map<String, Object> rootMap = Maps.newHashMap();
            rootMap.put("table", table);
            rootMap.put("cfg", genConf);

            File baseBeanTemplate = ResourceUtils.getFile("classpath:templates/bean.ftl");
            File beanMapperTemplate = ResourceUtils.getFile("classpath:templates/bean_mapper.ftl");
            File beanServiceTemplate = ResourceUtils.getFile("classpath:templates/bean_service.ftl");
            File beanControllerTemplate = ResourceUtils.getFile("classpath:templates/bean_controller.ftl");
            File beanMapperXmlTemplate = ResourceUtils.getFile("classpath:templates/bean_mapper_xml.ftl");

            FileWriter baseBeanWriter = new FileWriter(baseDbBeanPackage.getAbsolutePath() + "/" + table.getBeanName() + ".java");
            FileWriter beanMapperWriter = new FileWriter(beanMapperPackage.getAbsolutePath() + "/" + table.getBeanName() + "Mapper.java");
            FileWriter beanServiceWriter = new FileWriter(beanServicePackage.getAbsolutePath() + "/" + table.getBeanName() + "Service.java");
            FileWriter beanControllerWriter = new FileWriter(beanControllerPackage.getAbsolutePath() + "/" + table.getBeanName() + "Controller.java");
            FileWriter beanMapperXmlWriter = new FileWriter(beanMapperXmlPackage.getAbsolutePath() + "/" + table.getBeanName() + "Mapper.xml");

            FreemarkerUtil.flushData(baseBeanTemplate.getAbsolutePath(), baseBeanWriter, rootMap);
            FreemarkerUtil.flushData(beanMapperTemplate.getAbsolutePath(), beanMapperWriter, rootMap);
            FreemarkerUtil.flushData(beanServiceTemplate.getAbsolutePath(), beanServiceWriter, rootMap);
            FreemarkerUtil.flushData(beanControllerTemplate.getAbsolutePath(), beanControllerWriter, rootMap);
            FreemarkerUtil.flushData(beanMapperXmlTemplate.getAbsolutePath(), beanMapperXmlWriter, rootMap);
        }


    }
}
