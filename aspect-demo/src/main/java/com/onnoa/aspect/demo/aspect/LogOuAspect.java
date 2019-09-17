package com.onnoa.aspect.demo.aspect;

import com.onnoa.aspect.demo.LongOu;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @Description: 切面类
 * @Author: onnoA
 * @Date: 2019/7/28 11:10
 */
@Aspect//声明这是一个切面
@Component//把这个类交给spring管理
public class LogOuAspect {
    // 把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.onnoa.aspect.demo.LongOu)")
    public void ouAspect() {
    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    //@Before("ouAspect()")
    public void begin() {
        System.out.println("前置增强，方法执行前 ： before");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    //@AfterReturning("ouAspect()")
    public void afterReturning() {
        System.out.println("后置增强，方法执行后 ： after");
    }


    // 在这里定义前置切面
    //@Before("ouAspect()")
    // 后置切面
    //@AfterReturning("ouAspect()")
    // 环绕切面
    @Around("ouAspect()")
    public void beforeMethod(ProceedingJoinPoint  joinPoint) throws Throwable {
        // 这里执行保存日志的动作
        System.out.println("目标方法执行前.......");
        //得到被切方法的参数
        System.out.println(joinPoint.getArgs()[0]);
        System.out.println("打印被切方法的参数:"+joinPoint.getArgs());

        // 得到被切方法的方法名 getUserTest
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        LongOu longOu = getDeclaredAnnotation(joinPoint);
        System.out.println("==@Around== longOu logger --》 method name " + methodName + " args " + params[0]);
        if(joinPoint.getArgs() != null){
            System.out.println("参数不为空");
            // 执行源方法
            joinPoint.proceed();
        }
        else{

        }

        // 模拟进行验证
        if (params != null && params.length > 0 ) {
            System.out.println("==@Around== longOu logger --》 " + longOu.module() + " auth success");
        } else {
            System.out.println("==@Around== longOu logger --》 " + longOu.module() + " auth failed");
        }
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public LongOu getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        LongOu annotation = objMethod.getDeclaredAnnotation(LongOu.class);
        // 返回
        return annotation;
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
   /* public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(PayFilter. class).description();
                    LOGGER.info("======"+description+"======");
                    break;
                }
            }
        }
        return description;
    }

    *//**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     *//*
    public PayFilter getDeclaredAnnotation(ProceedingJoinPoint joinPoint) {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = null;
        PayFilter annotation = null;
        try {
            objMethod = targetClass.getMethod(methodName, parameterTypes);
            // 拿到方法定义的注解信息
            annotation = objMethod.getDeclaredAnnotation(PayFilter.class);
            // 返回
            return annotation;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return annotation;
        }

    }*/
}
