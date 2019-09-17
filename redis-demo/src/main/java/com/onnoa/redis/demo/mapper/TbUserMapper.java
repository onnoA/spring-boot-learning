package com.onnoa.redis.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.onnoa.redis.demo.entity.TbUser;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/5 01:02
 */
@MapperScan
public interface TbUserMapper extends BaseMapper<TbUser> {

}
