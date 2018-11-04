package ${cfg.beanServicePackage};

import com.recruit.base.annotation.RestInfo;
import com.recruit.base.dto.RespDTO;
import ${cfg.baseDbBeanPackage}.${table.beanName};
import ${cfg.beanServicePackage}.${table.beanName}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.recruit.base.constant.Constants;

/**
* Author:nicky_x
* Description:${table.beanName} Controller
* Date:Created in ${.now}
* Copyright (c)  xdy_0722@sina.com All Rights Reserved.
*/
@RestController
@RequestMapping("/${table.beanName}")
public  class ${table.beanName}Controller {

private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ${table.beanName}Service service;

    /**
    * 新增
    *
    * @param vo
    * @return
    */
    @RestInfo(name = "add")
    @PostMapping("/add")
    public RespDTO add(${table.beanName} vo) {
        logger.info("/add,vo:{}", vo);
        try {
            return return service.insert(vo);
        } catch (ServiceException e) {
            logger.error("/add fail,", e);
            return RespDTO.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("/add fail,", e);
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
        }
    }

    /**
    * 分页
    *
    * @param vo
    * @return
    */
    @RestInfo(name = "findByPage")
    @GetMapping("/page")
    public RespDTO findByPage(${table.beanName} vo) {
        logger.info("/page,vo:{}", vo);
        try {
            return service.findByPage(vo);
        } catch (ServiceException e) {
            logger.error("/page fail,", e);
            return RespDTO.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("/page fail,", e);
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
        }
    }

    /**
    * 主键
    *
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public RespDTO findById(@PathVariable Long id) {
        logger.info("/findById,id:{}", id);
        try {
            return service.getById(id);
        } catch (ServiceException e) {
            logger.error("/findById fail,", e);
            return RespDTO.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("/findById fail,", e);
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
        }
    }

    /**
    * 更新
    *
    * @param vo
    * @return
    */
    @RestInfo(name = "update")
    @PostMapping("/update")
    public RespDTO update(${table.beanName} vo) {
        logger.info("/update,vo:{}", vo);
        try {
            service.updateIgnoreNull(vo);
        } catch (ServiceException e) {
            logger.error("/update fail,", e);
            return RespDTO.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("/update fail,", e);
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
        }
    }

    /**
    * 删除
    *
    * @param id
    * @return
    */
    @RestInfo(name = "delete")
    @PostMapping("/delete")
    public RespDTO delete(Long id) {
        logger.info("/delete,id:{}", id);
        try {
            return service.delete(id);
        } catch (ServiceException e) {
            logger.error("/update fail,", e);
            return RespDTO.fail(e.getMessage());
        } catch (Exception e) {
            logger.error("/update fail,", e);
            return RespDTO.fail(Constants.NET_BUSY_ERROR_INFO);
        }
    }
}