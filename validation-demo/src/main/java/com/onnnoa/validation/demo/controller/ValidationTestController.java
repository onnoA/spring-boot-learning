package com.onnnoa.validation.demo.controller;

import com.onnnoa.validation.demo.entity.TbUser;
import com.onnnoa.validation.demo.exception.SimpleException;
import com.onnnoa.validation.demo.exception.enums.SimpleExceptionEnums;
import com.onnnoa.validation.demo.respone.ResultBean;
import com.onnnoa.validation.demo.utils.ErrorUtil;
import com.onnnoa.validation.demo.utils.ValidationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

            return ErrorUtil.getErrorBean(SimpleExceptionEnums.INPUT_PARAMS_ERROR,e.getMsg());
        }
        return ResultBean.success(tbUser);
    }
}
