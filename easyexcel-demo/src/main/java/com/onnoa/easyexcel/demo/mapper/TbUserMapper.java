package com.onnoa.easyexcel.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.easyexcel.demo.entity.TbUser;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 17:43
 */
@MapperScan
public interface TbUserMapper extends BaseMapper<TbUser> {

}
