package com.onnoa.easyexcel.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
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

    //@ExcelProperty(value = "用户名",index = 0)
    @TableField(value = "username")
    private String username;

    //@ExcelProperty(value = "密码",index = 1)
    @TableField(value = "password")
    private String password;

    //@ExcelProperty(value = "电话",index = 2)
    @TableField(value = "phone")
    private String phone;

    //@ExcelProperty(value = "邮箱",index = 3)
    @TableField(value = "email")
    private String email;

    //@ExcelProperty(value = "创建时间",index = 4,format = "yyyyMMdd:hhmmss")
    @TableField(value = "created")
    private Date created;

    //@ExcelProperty(value = "更新时间",index = 5)
    @TableField(value = "updated")
    private Date updated;

}
