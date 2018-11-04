package com.dy.bom.code.dao;

import com.dy.bom.code.bean.Column;
import com.dy.bom.code.bean.Table;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Author:nicky_x
 * Description:表Dao
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
@Repository
public class TableDao {
    private static final Logger logger = LoggerFactory.getLogger(TableDao.class);
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取数据库-表
     * @param schema
     * @return
     */
    public List<Table> getTableList(String schema) {
        List<Table> dataList = Lists.newArrayList();
        try {
            String sql = "SELECT table_name,table_comment,table_rows,data_length FROM information_schema.tables WHERE table_schema = '" + schema + "'";
            logger.info("数据库表SQL:{} ", sql);
            dataList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Table.class));
        } catch (Exception e) {
            logger.error("表获取失败，数据库:{}", schema, e);
        }
        return dataList;
    }

    /**
     * 获取表-列名
     * @param schema
     * @param tableName
     * @return
     */
    public List<Column> getColumnList(String schema, String tableName) {
        List<Column> dataList = Lists.newArrayList();
        try {
            String sql = "select column_name,data_type,column_comment from information_schema.COLUMNS where TABLE_SCHEMA='" + schema + "' and table_name='" + tableName + "'";
            logger.info("数据库表-列SQL:{} ", sql);
            dataList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Column.class));
        } catch (Exception e) {
            logger.error("表-列获取失败，数据库:{} ,表:{}", schema, tableName, e);
        }
        return dataList;
    }

    /**
     * 获取表主键
     * @param schema
     * @param tableName
     * @return
     */
    public List<String> getPKList(String schema, String tableName) {
        List<String> dataList = null;
        try {
            StringBuffer sb = new StringBuffer("");
            sb.append("SELECT COLUMN_NAME              ");
            sb.append("FROM INFORMATION_SCHEMA.COLUMNS ");
            sb.append("WHERE TABLE_SCHEMA = '" + schema + "'     ");
            sb.append("   AND TABLE_NAME = '" + tableName + "'   ");
            sb.append("   AND COLUMN_KEY = 'PRI';      ");
            dataList = jdbcTemplate.queryForList(sb.toString(), String.class);
        } catch (Exception e) {
            logger.error("表主键获取失败 fail db = {} ,table = {} ", schema, tableName, e);
        }
        return dataList;
    }

}
