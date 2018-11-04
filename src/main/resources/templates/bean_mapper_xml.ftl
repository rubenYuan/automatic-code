<#assign crud_columns = [] />
<#assign columns = table.columnList />
<#list columns as col>
    <#if !(col.columnName =='id' || col.columnName == 'created' || col.columnName == 'modified')>
        <#assign crud_columns = crud_columns + [col] />
    </#if>
</#list>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${cfg.beanMapperPackage}.${table.beanName}Mapper">
    <!--查询字段-->
    <sql id="columns">
    <#list columns as col>
        a.`${col.columnName}`<#if col_has_next>,</#if>
    </#list>
    </sql>

    <!--查询结果集-->
    <resultMap id="beanMap" type="${cfg.baseDbBeanPackage}.${table.beanName}">
    <#list columns as col>
        <result property="${col.propertyName}" column="${col.columnName}"/>
    </#list>
    </resultMap>

    <!--查询-->
    <sql id="query">
        SELECT *
        FROM ${table.tableName}
    </sql>

    <!-- 新增记录 -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id"
            parameterType="${cfg.baseDbBeanPackage}.${table.beanName}">
        INSERT INTO ${table.tableName}
        (
    <#list crud_columns as col>
        `${col.columnName}`<#sep>,
    </#list>

        )
        VALUES
        (
    <#list crud_columns as col>
        <#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse><#sep>,
    </#list>

        )
    </insert>

    <!--更新实体信息，null值字段不更新-->
    <update id="updateIgnoreNull" parameterType="${cfg.baseDbBeanPackage}.${table.beanName}">
        UPDATE ${table.tableName}
        <set>
        <#list crud_columns as col>
            <if test="${col.propertyName} != null">
                `${col.columnName}` = <#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse>,
            </if>
        </#list>
        </set>
        WHERE  <#list table.pkColumnList as pk><#if pk_index gt 0> AND</#if> ${pk} = <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse></#list>
    </update>

    <!--所有-->
    <select id="findAll" resultMap="beanMap">
        <include refid="query"/>
        <include refid="condition"/>
        ORDER BY id DESC
    </select>

    <!--分页查询-->
    <select id="listByPage" resultMap="beanMap">
        <include refid="query"/>
        <include refid="condition"/>
        ORDER BY id DESC
    </select>

    <!--根据主键获取实体-->
    <select id="getById" resultMap="beanMap">
        <include refid="query"/>
        WHERE <#list table.pkColumnList as pk><#if pk_index gt 0> AND</#if> ${pk}= <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse></#list>
    </select>

    <!--根据主键删除实体-->
    <delete id="delete">
        DELETE FROM ${table.tableName}
        WHERE <#list table.pkColumnList as pk><#if pk_index gt 0> AND</#if> ${pk}= <#noparse>#{</#noparse>${table.pkPropertyList[pk_index]}<#noparse>}</#noparse></#list>
    </delete>


    <!--条件查询-->
    <sql id="condition">
        <where>
        <#list crud_columns as col>
            <if test="${col.propertyName} != null">
                AND `${col.columnName}` = <#noparse>#{</#noparse>${col.propertyName}<#noparse>}</#noparse>
            </if>
        </#list>
        </where>
    </sql>

</mapper>