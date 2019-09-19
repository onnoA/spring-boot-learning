package com.onnoa.practica.test;

import org.apache.commons.lang3.CharSequenceUtils;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/10 10:30
 */
public class ReflectTest {

    public static void main(String args[]) {
        TbUser tbUser = new TbUser();
        // 获取TbUser对象所有的字段数组
        Field[] declaredFields = tbUser.getClass().getDeclaredFields();
        System.out.println("获取的TbUser所有字段:" + declaredFields);
        for (Field declaredField : declaredFields) {
            // 获取字段的名字
            String fieldName = declaredField.getName();
            System.out.println("获取字段的名字为：" + fieldName);
            //
            if ("username".equals(fieldName)) {
                // 获取字段类型
                if ("class java.lang.String".equals(declaredField.getGenericType().toString())) {
                    System.out.println("字段类型为：" + declaredField.getGenericType());
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
