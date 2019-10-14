package com.onnoa.practica.test;

import com.onnoa.practica.annotation.FieldNotNull;
import com.onnoa.practica.entity.ValidateResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description: 自定义注解对前端传的参数进行校验
 * @Author: onnoA
 * @Date: 2019/9/10 10:30
 */
public class ReflectTest {

    public static void main(String args[]) {
        List<ValidateResult> validateResults = new ArrayList<>();
        TbUser tbUser = new TbUser();
        tbUser.setPhone("1342212673");
        tbUser.setEmail("onnoahen@165.c");
        // 获取TbUser对象所有的字段数组
        Field[] declaredFields = tbUser.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("获取的TbUser所有字段:" +declaredField.getName());
        }
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object v = field.get(tbUser);
                System.out.println(v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (field.isAnnotationPresent(FieldNotNull.class)) {
                // 打破访问权限，使得在类的外面获取此类的私有成员变量
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(tbUser);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value == null || "".equals(value)) {
                    FieldNotNull notNull = field.getAnnotation(FieldNotNull.class);
                    ValidateResult validateResult = new ValidateResult();
                    validateResult.setMessage(notNull.message());
                    validateResults.add(validateResult);
                }
                // 校验手机格式
                else if ("phone".equals(field.getName())) {

                    if (!isPhone(value.toString())) {
                        ValidateResult result = new ValidateResult();
                        result.setMessage("电话号码格式错误");
                        validateResults.add(result);
                    }
                }
                // 校验邮箱格式
                else if ("email".equals(field.getName())) {

                    if (!isEmail(value.toString())) {
                        ValidateResult result = new ValidateResult();
                        result.setMessage("邮箱格式错误");
                        validateResults.add(result);
                    }
                }
                //  TODO其他校验
            }
            //NotNull annotation = field.getAnnotation(NotNull.class);

            // 获取字段的名字
            String fieldName = field.getName();
            System.out.println("获取字段的名字为：" + fieldName);
            //
            if ("username".equals(fieldName)) {
                // 获取字段类型
                if ("class java.lang.String".equals(field.getGenericType().toString())) {
                    System.out.println("字段类型为：" + field.getGenericType());
                    try {
                        Method m = tbUser.getClass().getMethod("getUsername");
                        System.out.println("获取的get方法名为：" + m);
                        // 调用Method类代表的方法
                        Object v = m.invoke(tbUser);
                        System.out.println("获取的值为：" + v);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        validateResults.forEach(errorMsg -> System.out.println(errorMsg.getMessage()));

        getCheckResult(validateResults, null);
    }

    public static String getCheckResult(List<ValidateResult> validate, String expandMsg) {
        if (validate != null && validate.size() > 0) {
            StringBuffer sb = new StringBuffer();
            if (expandMsg != null) {
                sb.append(expandMsg);
            }
            for (int i = 0; i < validate.size(); i++) {
                if (i < validate.size() - 1) {
                    sb.append(validate.get(i).getMessage()).append(",");
                }else{
                    sb.append(validate.get(i).getMessage());
                }

            }
            System.out.println(sb);
            System.out.println("=========================分隔符==================");
            StringBuffer buffer = new StringBuffer();
            for (ValidateResult result : validate) {
                /*if(!result.isValid()){
                    sb.append(result.getMessage()).append(",");
                }*/
                buffer.append(result.getMessage()).append(",");
            }
            System.out.println(buffer.deleteCharAt(buffer.length()-1));
            return sb.toString();

        }
        return null;
    }

    private static boolean isEmail(String email) {
        String emailRegex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern compile = Pattern.compile(emailRegex);
        return compile.matcher(email).matches();
    }

    private static boolean isPhone(String phone) {
        String phoneRegex = "(134[0-8]\\d{7})" + "|(" + "((13([0-3]|[5-9]))" + "|149"
                + "|15([0-3]|[5-9])" + "|166" + "|17(3|[5-8])" + "|18[0-9]" + "|19[8-9]" + ")" + "\\d{8}" + ")";
        Pattern compile = Pattern.compile(phoneRegex);
        return compile.matcher(phone).matches();
    }


    public void print() {
        System.out.println("helloworld");
    }

    public int print(int a, int b) {
        return a + b;
    }

    public String print(String a, String b) {
        return (a + b);
    }
}
