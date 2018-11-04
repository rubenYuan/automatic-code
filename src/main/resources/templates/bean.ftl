package ${cfg.baseDbBeanPackage};

<#if table.importBeanList??>
    <#list table.importBeanList as item>
import ${item};
    </#list>
</#if>
import com.recruit.base.bean.BaseEntity;
import lombok.Data;

/**
* Author:nicky_x
* Description:${table.beanName} Entity
* Date:Created in ${.now}
* Copyright (c)  xdy_0722@sina.com All Rights Reserved.
*/
@Data
public class ${table.beanName} extends BaseEntity{
<#list table.propertyList as col>
    <#if !(col.columnName =='id' || col.columnName == 'created' || col.columnName == 'modified')>
        <#if col.columnComment?has_content>
        /**
         ** ${col.columnComment}
        **/
        </#if>
        private ${col.propertyType} ${col.propertyName};
    </#if>
</#list>
}
