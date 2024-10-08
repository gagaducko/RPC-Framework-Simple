package rpc.gagaduck.common.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类，提供创建对象、获取公共方法、调用方法等功能。
 */
public class ReflectionUtils {

    /**
     * 使用反射创建一个类的实例。
     *
     * @param clazz 需要创建实例的类
     * @param <T>   类的类型
     * @return 创建的实例对象
     * @throws IllegalStateException 如果实例化过程中发生异常
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    /**
     * 获取指定类的所有公共方法。
     *
     * @param clazz 需要获取公共方法的类
     * @return 该类的所有公共方法数组
     */
    public static Method[] getPublicMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> publicMethods = new ArrayList<>();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                publicMethods.add(m);
            }
        }
        return publicMethods.toArray(new Method[0]);
    }

    /**
     * 使用反射调用对象的方法。
     *
     * @param obj    要调用方法的对象
     * @param method 要调用的方法
     * @param args   调用方法时传递的参数
     * @return 方法调用的结果
     * @throws IllegalStateException 如果方法调用过程中发生异常
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}