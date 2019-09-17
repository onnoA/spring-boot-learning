package com.onnoa.poi.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.poi.demo.entity.TbUser;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 17:43
 */
@MapperScan
public interface TbUserMapper extends BaseMapper<TbUser> {

}
