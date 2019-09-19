package com.onnoa.practica.test;

import com.onnoa.practica.annotation.FieldNotNull;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/10 10:31
 */
@Data
//@Accessors(chain = true)
public class TbUser  implements Serializable {

    private int id;

    private String username;

    private String password;

    @FieldNotNull(message = "手机号码不能为空")
    @NotNull(message = "电话号码不能为空")
    @Pattern(regexp = "\"(134[0-8]\\\\d{7})\" + \"|(\" + \"((13([0-3]|[5-9]))\" + \"|149\"\n" +
            "            + \"|15([0-3]|[5-9])\" + \"|166\" + \"|17(3|[5-8])\" + \"|18[0-9]\" + \"|19[8-9]\" + \")\" + \"\\\\d{8}\" + \")\"",
            message = "请输入正确的手机号")
    private String phone;

    @FieldNotNull(message = "邮箱不能为空")
    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",
    message = "请输入正确的邮箱")
    private String email;

    private Date created;

    private Date updated;

   /* public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }*/
}
