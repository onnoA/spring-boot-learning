package com.onnoa.easyexcel.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/12 10:43
 */
@Data
public class TbUserDto extends BaseRowModel {

    @ExcelProperty(value = "用户名",index = 0)
    private String username;

    @ExcelProperty(value = "密码",index = 1)
    private String password;

    @ExcelProperty(value = "电话",index = 2)
    private String phone;

    @ExcelProperty(value = "邮箱",index = 3)
    private String email;

    @ExcelProperty(value = "创建时间",index = 4)
    private Date created;

    @ExcelProperty(value = "更新时间",index = 5)
    private Date updated;

}
