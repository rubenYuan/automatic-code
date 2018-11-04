package com.dy.bom.code;

import com.dy.bom.code.service.CodeGenService;
import com.dy.bom.code.util.SpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutomaticCodeApplication {

	public static void main(String[] args) throws Exception{
        SpringBeanUtil.context = SpringApplication.run(AutomaticCodeApplication.class, args);
        CodeGenService codeGenService = SpringBeanUtil.getBean(CodeGenService.class);
        System.out.println("<<<<<<<<<<<<<<<<<<<<生成代码开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        codeGenService.genCode();
        System.out.println("<<<<<<<<<<<<<<<<<<<<生成代码成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
}
