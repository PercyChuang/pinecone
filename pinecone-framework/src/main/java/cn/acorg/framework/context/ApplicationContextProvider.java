package cn.acorg.framework.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文提供者
 * @author Z-BL
 * @date 2020年02月11日 10:32:06
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 获取Spring 上下文
     * @author 松果
     * @date 2020/10/29 14:45
     * @return org.springframework.context.ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过 name 获取 组件名 返回指定的 Bean 组件
     * @param name Spring容器 组件注入时声明的名称
     * @author 松果
     * @date 2020/10/29 14:49
     * @return java.lang.Object
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过 class 获取指定的 Bean 组件
     * @param clazz 组件类的实例类型
     * @author 松果
     * @date 2020/10/29 14:48
     * @return T
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过 name 以及 Clazz 获取指定的 Bean 组件
     * @param name Spring容器 组件注入时声明的名称
     * @param clazz 组件类的实例类型
     * @author 松果
     * @date 2020/10/29 14:48
     * @return T
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 根据组件名称 判断 容器中是否包含此组件
     * @param name 组件名称
     * @author 松果
     * @date 2020/10/29 16:34
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断 此组件是否单例模式
     * @param name 组件名称
     * @author 松果
     * @date 2020/10/29 16:33       
     * @return boolean
     */ 
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 根据 组件名称 获取此组件实例类型
     * @param name 组件名称
     * @author 松果
     * @date 2020/10/29 16:34
     * @return java.lang.Class<? extends java.lang.Object>
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }


}
