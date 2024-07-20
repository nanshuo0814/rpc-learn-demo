package com.nanshuo.rpcprovider;

import com.nanshuo.rpccode.RpcApplication;
import com.nanshuo.rpccode.config.RegistryConfig;
import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.model.ServiceMetaInfo;
import com.nanshuo.rpccode.registry.LocalRegistryCenter;
import com.nanshuo.rpccode.registry.Registry;
import com.nanshuo.rpccode.registry.RegistryFactory;
import com.nanshuo.rpccode.server.HttpServer;
import com.nanshuo.rpccode.server.VertxHttpServer;
import com.nanshuo.rpccommon.service.UserService;

import java.util.ArrayList;

/**
 * 提供程序示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class ProviderExample {

    public static void main(String[] args) {
        // rpc 框架初始化
        RpcApplication.init();
        // 注册服务
        LocalRegistryCenter.register(UserService.class.getName(), UserServiceImpl.class);
        // 将服务注册到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(UserService.class.getName());
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try{
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 启动Web服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}