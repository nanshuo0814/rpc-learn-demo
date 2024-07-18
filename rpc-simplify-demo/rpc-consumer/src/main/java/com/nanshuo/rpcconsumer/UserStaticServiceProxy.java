package com.nanshuo.rpcconsumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.nanshuo.rpccode.model.RpcRequest;
import com.nanshuo.rpccode.model.RpcResponse;
import com.nanshuo.rpccode.serializer.JdkSerializer;
import com.nanshuo.rpccode.serializer.Serializer;
import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;

import java.io.IOException;

/**
 * 用户静态服务代理
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class UserStaticServiceProxy implements UserService {

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link User }
     */
    @Override
    public User getUser(User user) {
        // 指定序列化器
        final Serializer serializer = new JdkSerializer();
        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder().serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        // 序列化（Java 对象 => 字节数组）
        try {
            byte[] bytes = serializer.serialize(rpcRequest);
            HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bytes).execute();
            byte[] result = httpResponse.bodyBytes();
            // 反序列化（字节数组 => Java 对象）
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
