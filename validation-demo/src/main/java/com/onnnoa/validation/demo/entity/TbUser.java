package com.onnnoa.validation.demo.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/20 23:47
 */

@Data
public class TbUser {

    private int id;

    //@ApiModelProperty(value = "用户名",name = "username",required = true )
    private String username;

    private String password;

    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{4,8}$",
            message = "请输入正确的手机号")
    private String phone;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
            message = "请输入正确的邮箱")
    private String email;

    private Date created;

    private Date updated;
}
