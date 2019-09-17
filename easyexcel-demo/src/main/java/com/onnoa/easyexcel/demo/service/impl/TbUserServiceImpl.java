package com.onnoa.easyexcel.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.onnoa.easyexcel.demo.dto.TbUserDto;
import com.onnoa.easyexcel.demo.entity.TbUser;
import com.onnoa.easyexcel.demo.mapper.TbUserMapper;
import com.onnoa.easyexcel.demo.service.TbUserService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 17:47
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public List<TbUserDto> selectAll() {
        List<TbUserDto> tbUserDtoList = new ArrayList<>();
        List<TbUser> tbUsers = tbUserMapper.selectList(null);
        for (TbUser tbUser : tbUsers) {
            TbUserDto tbUserDto = new TbUserDto();
            BeanUtils.copyProperties(tbUser,tbUserDto);
            tbUserDtoList.add(tbUserDto);
        }
        return tbUserDtoList;
    }
}
