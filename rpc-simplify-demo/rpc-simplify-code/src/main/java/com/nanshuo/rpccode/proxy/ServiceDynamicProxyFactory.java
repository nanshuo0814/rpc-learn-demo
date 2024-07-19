package com.nanshuo.rpccode.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务动态代理工厂
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class ServiceDynamicProxyFactory {

    /**
     * 获取代理
     *
     * @param clazz 克拉兹
     * @return {@link T }
     */
    public static <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new JdkDynamicServiceProxy()
        );
    }
}
