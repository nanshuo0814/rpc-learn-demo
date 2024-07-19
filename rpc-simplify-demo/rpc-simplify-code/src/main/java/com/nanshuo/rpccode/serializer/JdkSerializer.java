package com.nanshuo.rpccode.serializer;


import java.io.*;

/**
 * jdk序列化器实现
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class JdkSerializer implements Serializer {

    /**
     * 序列化
     *
     * @param object 对象
     * @return {@link byte[] }
     * @throws IOException ioexception
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        oos.close();
        return baos.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param bytes 字节数
     * @param clazz 要反序列化的对象
     * @return {@link T }
     * @throws IOException ioexception
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        ObjectInputStream ois = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            assert ois != null;
            ois.close();
        }
    }
}
