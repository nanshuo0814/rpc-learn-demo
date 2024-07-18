package com.nanshuo.rpcprovider;

import com.nanshuo.rpccode.registry.LocalRegistryCenter;
import com.nanshuo.rpccommon.service.UserService;

/**
 * 提供程序启动示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class ProviderStartedExample {

    public static void main(String[] args) {
        // 注册中心提供注册服务
        LocalRegistryCenter.register(UserService.class.getName(), UserServiceImpl.class);
        // todo 使用Vert.x 启动 web 服务，开启端口，例如：8080
    }

}
