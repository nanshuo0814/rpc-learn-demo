package com.nanshuo.rpccomsumer;

import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.utils.ConfigUtils;

/**
 * 服务消费者示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
