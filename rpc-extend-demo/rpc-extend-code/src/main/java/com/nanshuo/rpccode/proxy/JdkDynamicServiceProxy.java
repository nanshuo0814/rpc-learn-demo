package com.nanshuo.rpccode.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.nanshuo.rpccode.RpcApplication;
import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.constant.RpcConstant;
import com.nanshuo.rpccode.model.RpcRequest;
import com.nanshuo.rpccode.model.RpcResponse;
import com.nanshuo.rpccode.model.ServiceMetaInfo;
import com.nanshuo.rpccode.registry.Registry;
import com.nanshuo.rpccode.registry.RegistryFactory;
import com.nanshuo.rpccode.serializer.JdkSerializer;
import com.nanshuo.rpccode.serializer.Serializer;
import com.nanshuo.rpccode.serializer.SerializerFactory;
import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * jdk动态服务代理
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class JdkDynamicServiceProxy implements InvocationHandler {

    /**
     * 调用
     *
     * @param proxy  代理
     * @param method 方法
     * @param args   args
     * @return {@link Object }
     * @throws Throwable 可抛出
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serverName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder().serviceName(serverName)
                .methodName(method.getName())
                .parameterTypes(new Class[]{User.class})
                .args(args)
                .build();
        // 序列化（Java 对象 => 字节数组）
        try {
            byte[] bytes = serializer.serialize(rpcRequest);
            // 从服务中心获取服务提供者地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serverName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无该服务地址");
            }
            ServiceMetaInfo selectServiceMetaInfo = serviceMetaInfoList.get(0);
            // 发送请求
            try (HttpResponse httpResponse = HttpRequest.post(selectServiceMetaInfo.getServiceAddress()).body(bytes).execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化（字节数组 => Java 对象）
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
