package com.nanshuo.rpccode.proxy;

import cn.hutool.core.collection.CollUtil;
import com.nanshuo.rpccode.RpcApplication;
import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.constant.RpcConstant;
import com.nanshuo.rpccode.fault.retry.RetryStrategy;
import com.nanshuo.rpccode.fault.retry.RetryStrategyFactory;
import com.nanshuo.rpccode.fault.tolerant.TolerantStrategy;
import com.nanshuo.rpccode.fault.tolerant.TolerantStrategyFactory;
import com.nanshuo.rpccode.loadbalancer.LoadBalancer;
import com.nanshuo.rpccode.loadbalancer.LoadBalancerFactory;
import com.nanshuo.rpccode.model.RpcRequest;
import com.nanshuo.rpccode.model.RpcResponse;
import com.nanshuo.rpccode.model.ServiceMetaInfo;
import com.nanshuo.rpccode.registry.Registry;
import com.nanshuo.rpccode.registry.RegistryFactory;
import com.nanshuo.rpccode.serializer.Serializer;
import com.nanshuo.rpccode.serializer.SerializerFactory;
import com.nanshuo.rpccode.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jdk动态服务代理
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class JdkDynamicServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        // 从注册中心获取服务提供者请求地址
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
        if (CollUtil.isEmpty(serviceMetaInfoList)) {
            throw new RuntimeException("暂无服务地址");
        }

        // 负载均衡
        LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
        // 将调用方法名（请求路径）作为负载均衡参数
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", rpcRequest.getMethodName());
        ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
        // rpc 请求
        // 使用重试机制
        RpcResponse rpcResponse;
        try {
            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
            );
        } catch (Exception e) {
            // 容错机制
            TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
            rpcResponse = tolerantStrategy.doTolerant(null, e);
        }
        return rpcResponse.getData();
    }
}
