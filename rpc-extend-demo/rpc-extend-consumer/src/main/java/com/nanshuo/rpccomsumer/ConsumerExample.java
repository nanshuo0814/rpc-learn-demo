package com.nanshuo.rpccomsumer;

import com.nanshuo.rpccode.bootstrap.ConsumerBootstrap;
import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.proxy.JdkDynamicServiceProxy;
import com.nanshuo.rpccode.proxy.ServiceDynamicProxyFactory;
import com.nanshuo.rpccode.utils.ConfigUtils;
import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;

/**
 * 服务消费者示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class ConsumerExample {

    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ServiceDynamicProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("nanshuo");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
