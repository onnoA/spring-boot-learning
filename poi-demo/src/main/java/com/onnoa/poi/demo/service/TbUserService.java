package com.onnoa.poi.demo.service;


import com.onnoa.poi.demo.dto.TbUserDto;

import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 17:45
 */
public interface TbUserService {

   List<TbUserDto> selectAll();

}
