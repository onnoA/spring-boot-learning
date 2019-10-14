package com.onnoa.practica.test;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/9/10 10:31
 */
public class Practice3 {


    public static void main(String[] args) {


        String money = "4.70";
        BigDecimal yuanBd = new BigDecimal(money);
        long l1 = yuanBd.movePointRight(2).longValue();
        System.out.println(l1);
        BigDecimal bigDecimal = new BigDecimal(money);
        BigDecimal bigDecimal1 = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal1);


        System.out.println();
        Double anew = Double.parseDouble(money);
        Long mon1 = new Double(anew * 100.0).longValue();
        Number num =Float.parseFloat(money)*100;
        Long mon2 = Long.valueOf(num.intValue());
        System.out.println(mon1);
        System.out.println(mon2);

        Integer a = 10;
        Integer b = 10;
        Integer c = 1297;
        Integer d = 1297;
        Long l = 1l;
        Long j = 1l;
        Long k = 222l;
        Long m = 222l;
        System.out.println(l == j);
        System.out.println(k == m);
        System.out.println(k.equals(m));
        System.out.println("==========================");
       // System.out.println(a - c);
        /*System.out.println(a == b);
        System.out.println(c.equals(d));*/
        int n = 222;
        int p = 222;
        System.out.println(n==p);
        /*String name = "工行APP";
        String name2 = "工行app";
        System.out.println(name.equalsIgnoreCase(name2));
        String amount = "4.00";
        Number num = Float.parseFloat(amount) * 100;
        System.out.println("转换后的金额：" + Long.valueOf(num.intValue()));
*/

       /* TbUser tbUser = new TbUser();
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
        }*/
    }
}
