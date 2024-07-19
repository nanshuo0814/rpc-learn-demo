package com.nanshuo.rpccode.proxy;

import com.nanshuo.rpccode.RpcApplication;

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
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(clazz);
        }
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new JdkDynamicServiceProxy()
        );
    }

    /**
     * 根据服务类获取 Mock 代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }

}
