package com.onnoa.redis.demo.service.impl;

import com.onnoa.redis.demo.constant.TbUserRedisConstant;
import com.onnoa.redis.demo.entity.TbUser;
import com.onnoa.redis.demo.mapper.TbUserMapper;
import com.onnoa.redis.demo.service.TbUserService;
import com.onnoa.redis.demo.utils.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/8/5 09:11
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public void tbUserRedisTest(Long id) {
        TbUser tbUser = tbUserMapper.selectById(id);
        Integer random = (int) ((Math.random() * 9 + 1) * 100000);
        TbUserRedisConstant tbUserRedisKey = TbUserRedisConstant.tbUserRedisKey(1000);
        redisUtils.set(tbUserRedisKey,tbUser.getUsername(),random.toString());
    }
}
