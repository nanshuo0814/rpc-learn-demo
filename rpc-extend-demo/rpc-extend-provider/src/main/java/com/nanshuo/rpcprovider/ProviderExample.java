package com.nanshuo.rpcprovider;

import com.nanshuo.rpccode.RpcApplication;
import com.nanshuo.rpccode.bootstrap.ProviderBootstrap;
import com.nanshuo.rpccode.config.RegistryConfig;
import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.model.ServiceMetaInfo;
import com.nanshuo.rpccode.model.ServiceRegisterInfo;
import com.nanshuo.rpccode.registry.LocalRegistryCenter;
import com.nanshuo.rpccode.registry.Registry;
import com.nanshuo.rpccode.registry.RegistryFactory;
import com.nanshuo.rpccode.server.HttpServer;
import com.nanshuo.rpccode.server.VertxHttpServer;
import com.nanshuo.rpccode.server.tcp.VertxTcpServer;
import com.nanshuo.rpccommon.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 提供程序示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}