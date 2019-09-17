package com.onnoa.poi.demo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/12 10:43
 */
@Data
public class TbUserDto {

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

}
