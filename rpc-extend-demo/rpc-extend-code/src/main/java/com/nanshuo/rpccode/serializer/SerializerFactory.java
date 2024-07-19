package com.nanshuo.rpccode.serializer;

import com.nanshuo.rpccode.serializer.JdkSerializer;
import com.nanshuo.rpccode.serializer.Serializer;
import com.nanshuo.rpccode.spi.SpiLoader;

/**
 * 序列化器工厂（工厂模式，用于获取序列化器对象）
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}
