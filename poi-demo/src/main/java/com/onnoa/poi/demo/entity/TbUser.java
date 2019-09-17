package com.onnoa.poi.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/11 17:44
 */
@Data
@TableName(value = "tb_user")
public class TbUser implements Serializable {

    @TableId(value = "id")
    private String id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "created")
    private Date created;

    @TableField(value = "updated")
    private Date updated;

}
