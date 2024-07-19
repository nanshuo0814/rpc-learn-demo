package com.nanshuo.rpccode.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object 对象
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param clazz 要反序列化的类
     * @return {@link T }
     * @throws IOException ioexception
     */
    <T> T deserialize(byte[] bytes,Class<T> clazz) throws IOException;

}
