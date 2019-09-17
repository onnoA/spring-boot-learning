package com.onnoa.poi.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 23:00
 */
@Configuration
@MapperScan("com.onnoa.poi.demo.mapper")
public class MyBatisConfig {
}
