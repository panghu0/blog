package com.zhongrui.codegeneration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pang hu
 * @date 2020/10/18
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zhongrui.codegeneration.dao")
public class GeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratorApplication.class, args);
	}
	
}
