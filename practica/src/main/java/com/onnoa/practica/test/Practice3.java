package com.onnoa.practica.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/10 10:31
 */
public class Practice3 {

    public static void main(String[] args) {

        TbUser tbUser = new TbUser();
        Field[] field = tbUser.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
        System.out.println(field);
        try {
            for (int j = 0; j < field.length; j++) { // 遍历所有属性
                String name = field[j].getName(); // 获取属性的名字
                System.out.println("属性的名字:" + j + ":" + name);
                name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                System.out.println("将属性的首字符转为大写：" + name);
                String type = field[j].getGenericType().toString(); // 获取属性的类型
                System.out.println("属性的类型:" + type);
                if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                    Method m = tbUser.getClass().getMethod("get" + name);
                    System.out.println("m1:" + m);
                    String value = (String) m.invoke(tbUser); // 调用getter方法获取属性值
                    if (value == null) {
                        m = tbUser.getClass().getMethod("set" + name, String.class);
                        System.out.println("m2:" + m);
                        m.invoke(tbUser, "");
                    }
                }
                if (type.equals("class java.lang.Integer")) {
                    Method m = tbUser.getClass().getMethod("get" + name);
                    Integer value = (Integer) m.invoke(tbUser);
                    if (value == null) {
                        m = tbUser.getClass().getMethod("set" + name, Integer.class);
                        m.invoke(tbUser, 0);
                    }
                }
                if (type.equals("class java.lang.Boolean")) {
                    Method m = tbUser.getClass().getMethod("get" + name);
                    Boolean value = (Boolean) m.invoke(tbUser);
                    if (value == null) {
                        m = tbUser.getClass().getMethod("set" + name, Boolean.class);
                        m.invoke(tbUser, false);
                    }
                }
                if (type.equals("class java.util.Date")) {
                    Method m = tbUser.getClass().getMethod("get" + name);
                    Date value = (Date) m.invoke(tbUser);
                    if (value == null) {
                        m = tbUser.getClass().getMethod("set" + name, Date.class);
                        m.invoke(tbUser, new Date());
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
