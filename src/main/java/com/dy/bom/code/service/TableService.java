package com.dy.bom.code.service;

import com.dy.bom.code.util.StringUtil;
import com.dy.bom.code.bean.Column;
import com.dy.bom.code.bean.GenConf;
import com.dy.bom.code.bean.Table;
import com.dy.bom.code.dao.TableDao;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Author:nicky_x
 * Description:表Service
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Service
public class TableService {

    private static final Logger logger = LoggerFactory.getLogger(TableService.class);

    @Resource
    private TableDao tableDao;
    @Resource
    private GenConf genConf;

    private static final String BASE_ENTITY = "BaseEntity";
    private static final String ID_ENTITY = "IdEntity";
    private static final String DB_ENTITY = "DbEntity";
    private static final String U_ENTITY = "UEntity";

    /**
     * 获取表列表
     *
     * @return
     */
    public List<Table> getTableInfoList() {
        List<Table> dataList = tableDao.getTableList(genConf.getSchema());
        /**
         * 需要的表
         */
        List<Table> needDataList = Lists.newArrayList();
        String tableIgnored = genConf.getTableIgnored();
        String tableInclude = genConf.getTableInclude();
        List<String> tableIgnoredList = Lists.newArrayList();
        List<String> tableIncludeList = Lists.newArrayList();
        if (StringUtil.isNotBlank(tableIgnored)) {
            for (String str : tableIgnored.split(",")) {
                tableIgnoredList.add(str);
            }
        }
        if (StringUtil.isNotBlank(tableInclude)) {
            for (String str : tableInclude.split(",")) {
                tableIncludeList.add(str);
            }
        }
        for (Table data : dataList) {
            data.setColumnList(tableDao.getColumnList(genConf.getSchema(), data.getTableName()));
            data.setPkColumnList(tableDao.getPKList(genConf.getSchema(), data.getTableName()));
            if (CollectionUtils.isEmpty(data.getPkColumnList())) {
                logger.error("警告！！表：{}，没有主键！！！", data.getTableName());
            }

            List<String> pkPropertyList = Lists.newArrayList();
            for (String pk : data.getPkColumnList()) {
                pkPropertyList.add(StringUtil.toCamelStyle(pk));
            }

            Set<String> propertySet = Sets.newHashSet();
            Set<String> propertyTypeSet = Sets.newHashSet();
            for (Column column : data.getColumnList()) {
                propertySet.add(column.getPropertyName());
                propertyTypeSet.add(column.getPropertyType());
            }
            data.setPkPropertyList(pkPropertyList);

            data.setBaseBeanName(BASE_ENTITY);

            if (propertyTypeSet.contains("BigDecimal")) {
                data.getImportBeanList().add("java.math.BigDecimal");
            }
            if (propertyTypeSet.contains("Date")) {
                data.getImportBeanList().add("java.util.Date");
            }

            if (DB_ENTITY.equals(data.getBaseBeanName())) {
                data.getImportBeanList().remove("java.util.Date");
                for (Column column : data.getColumnList()) {
                    if (!"created".equals(column.getPropertyName()) && !"modified".equals(column.getPropertyName()) && column.getPropertyType().endsWith("Date")) {
                        data.getImportBeanList().add("java.util.Date");
                    }
                }
            }

            List<Column> propertyList = Lists.newArrayList();
            if (ID_ENTITY.equals(data.getBaseBeanName())) {
                for (Column column : data.getColumnList()) {
                    if ("id".equals(column.getPropertyName())) {
                        continue;
                    }
                    propertyList.add(column);
                }
            } else if (DB_ENTITY.equals(data.getBaseBeanName())) {
                for (Column column : data.getColumnList()) {
                    String propertyName = column.getPropertyName();
                    if ("id".equals(propertyName) || "created".equals(propertyName) || "modified".equals(propertyName)) {
                        continue;
                    }
                    propertyList.add(column);
                }
            } else if (U_ENTITY.equals(data.getBaseBeanName())) {
                for (Column column : data.getColumnList()) {
                    String propertyName = column.getPropertyName();
                    if ("id".equals(propertyName) || "created".equals(propertyName) || "modified".equals(propertyName) || "cuid".equals(propertyName) || "muid".equals(propertyName)) {
                        continue;
                    }
                    propertyList.add(column);
                }
            } else {
                propertyList = data.getColumnList();
            }

            data.setPropertyList(propertyList);

        }
        if (!CollectionUtils.isEmpty(tableIgnoredList)) {
            for (Table table : dataList) {
                for (String tableName : tableIgnoredList) {
                    if (table.getTableName().equalsIgnoreCase(tableName)) {
                        dataList.remove(table);
                    }
                }

            }
        }
        if (!CollectionUtils.isEmpty(tableIncludeList)) {
            for (Table table : dataList) {
                for (String tableName : tableIncludeList) {
                    if (table.getTableName().equalsIgnoreCase(tableName)) {
                        needDataList.add(table);
                    }
                }

            }
            dataList = needDataList;
        }

        return dataList;
    }
}
