package ${cfg.beanMapperPackage};

import ${cfg.beanBaseMapperPackage};
import ${cfg.baseDbBeanPackage}.${table.beanName};
import org.apache.ibatis.annotations.Mapper;

/**
* Author:nicky_x
* Description:${table.beanName} Mapper
* Date:Created in ${.now}
* Copyright (c)  xdy_0722@sina.com All Rights Reserved.
*/
@Mapper
public interface ${table.beanName}Mapper extends  BaseMapper<${table.beanName}> {

}