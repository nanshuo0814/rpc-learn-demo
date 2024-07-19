package com.nanshuo.rpccode.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class LocalRegistryCenter {

    /**
     * 使用 MAP 储存注册信息
     */
    private static final Map<String, Class<?>> MAP = new ConcurrentHashMap<>();

    /**
     * 注册
     *
     * @param serviceName 服务名称
     * @param clazz       服务实现类
     */
    public static void register(String serviceName, Class<?> clazz) {
        MAP.put(serviceName, clazz);
    }

    /**
     * 获取
     *
     * @param serviceName 服务名称
     * @return {@link Class }<{@link ? }>
     */
    public static Class<?> get(String serviceName) {
        return MAP.get(serviceName);
    }

    /**
     * 删除
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        MAP.remove(serviceName);
    }

}
