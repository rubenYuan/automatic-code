package com.dy.bom.code.bean;

import com.dy.bom.code.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author:nicky_x
 * Description:表-列
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public class Column extends BaseEntity {
    private static final Logger logger = LoggerFactory.getLogger(Column.class);

    private String columnName;
    private String dataType;
    private String columnComment;

    private String propertyName;
    private String propertyType;


    public String getPropertyName() {
        if (columnName.contains("_")) {
            propertyName = StringUtil.toCamelStyle(columnName);
        } else {
            propertyName = columnName;
        }
        return propertyName;
    }

    public String getPropertyType() {

        if ("varchar".equals(dataType) || "char".equals(dataType)) {
            propertyType = "String";
        }
        if ("text".equals(dataType) || "tinytext".equals(dataType) || "mediumtext".equals(dataType) || "longtext".equals(dataType)) {
            propertyType = "String";
        }
        if ("blob".equals(dataType) || "mediumblob".equals(dataType) || "tinyblob".equals(dataType)) {
            propertyType = "String";
        }
        if ("bigint".equals(dataType)) {
            propertyType = "Long";
        }
        if ("float".equals(dataType)) {
            propertyType = "Float";
        }
        if ("smallint".equals(dataType) || "mediumint".equals(dataType) || "tinyint".equals(dataType) || "int".equals(dataType)) {
            propertyType = "Integer";
        }
        if ("set".equals(dataType)) {
            propertyType = "Byte";
        }
        if ("decimal".equals(dataType)) {
            propertyType = "BigDecimal";
        }
        if ("timestamp".equals(dataType) || "date".equals(dataType) || "datetime".endsWith(dataType)) {
            propertyType = "Date";
        }

        if (StringUtil.isBlank(propertyType)) {
            logger.error("Type Error=========> {}", dataType);
        }

        return propertyType;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

}
