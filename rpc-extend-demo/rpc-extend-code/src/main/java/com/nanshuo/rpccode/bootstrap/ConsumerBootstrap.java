package com.nanshuo.rpccode.bootstrap;

import com.nanshuo.rpccode.RpcApplication;

/**
 * 服务消费者启动类（初始化）
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
public class ConsumerBootstrap {

    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }

}
