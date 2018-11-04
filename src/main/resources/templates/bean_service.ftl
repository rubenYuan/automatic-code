package ${cfg.beanServicePackage};

import com.recruit.base.constant.Constants;
import com.recruit.base.dto.RespDTO;
import ${cfg.baseDbBeanPackage}.${table.beanName};
import ${cfg.beanMapperPackage}.${table.beanName}Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.recruit.base.page.PageInfo;
import java.util.Date;
import java.util.List;

/**
* Author:nicky_x
* Description:${table.beanName} Service
* Date:Created in ${.now}
* Copyright (c)  xdy_0722@sina.com All Rights Reserved.
*/
@Service
public  class ${table.beanName}Service {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.beanName}Mapper mapper;

    /**
    * 新增数据
    *
    * @param vo
    * @return
    */
    public RespDTO insert(${table.beanName} vo) {
        try {
            vo.setCreated(new Date());
            return RespDTO.success(mapper.insert(vo));
        } catch (Exception e) {
            logger.error("insert fail,", e);
        }
        return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
}

    /**
    * 分页查询数据
    *
    * @param vo
    * @return
    */
    public RespDTO findByPage(${table.beanName} vo) {
        try {
            PageInfo pageInfo = PageInfo.threadLocal.get();
            List<${table.beanName}> list = mapper.findByPage(vo, pageInfo);
            pageInfo.setList(list);
            pageInfo.setTotalCount(mapper.findAll(vo).size());
            return RespDTO.success(pageInfo);
        } catch (Exception e) {
            logger.error(" findByPage fail,", e);
        }
        return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
    }

    /**
    * 根据主键获取数据
    *
    * @param id
    * @return
    */
    public RespDTO getById(Long id) {
         try {
             return RespDTO.success(mapper.getById(id));
         } catch (Exception e) {
             logger.error(" findAll fail,", e);
         }
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
    }

    /**
    * 非空更新数据
    *
    * @param vo
    * @return
    */
    public RespDTO update(${table.beanName} vo) {
        try {
            return RespDTO.success(mapper.update(vo));
        } catch (Exception e) {
            logger.error(" update fail,", e);
        }
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
    }

    /**
    * 物理删除-慎用
    *
    * @param id
    * @return
    */
    public RespDTO delete(Long id) {
        try {
             return RespDTO.success(mapper.delete(id));
        } catch (Exception e) {
            logger.error(" RespDTO fail,", e);
         }
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
    }
}