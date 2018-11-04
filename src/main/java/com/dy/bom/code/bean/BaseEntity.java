package com.dy.bom.code.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * Author:nicky_x
 * Description:基类，实现序列化接口
 * Date:Created in ${.now}
 * Copyright (c)  xdy_0722@sina.com All Rights Reserved.
 */
public abstract class BaseEntity implements Serializable {

	public String toString() {
		try {
			return this.getClass().getName() + " = " + JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
		} catch (Exception e) {
			return super.toString();
		}
	}
}