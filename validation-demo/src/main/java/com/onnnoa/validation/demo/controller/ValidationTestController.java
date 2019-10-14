package com.onnnoa.validation.demo.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.onnnoa.validation.demo.entity.TbUser;
import com.onnnoa.validation.demo.exception.SimpleException;
import com.onnnoa.validation.demo.exception.enums.SimpleExceptionEnums;
import com.onnnoa.validation.demo.respone.ResultBean;
import com.onnnoa.validation.demo.utils.ErrorUtil;
import com.onnnoa.validation.demo.utils.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/21 00:34
 */
@RestController
public class ValidationTestController {

    @GetMapping(value = "/validation")
    @ResponseBody
    public ResultBean<TbUser> validation(TbUser tbUser) {
        try {
            ValidationUtil.validate(tbUser);
        } catch (SimpleException e) {
            System.out.println(e.getMsg());
            return ResultBean.error(e.getCode(), e.getMsg());
        }

        return ResultBean.success(tbUser);
    }



}
