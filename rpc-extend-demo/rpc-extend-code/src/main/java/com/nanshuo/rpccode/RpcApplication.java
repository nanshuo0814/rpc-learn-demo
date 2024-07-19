package com.nanshuo.rpccode;

import com.nanshuo.rpccode.config.RpcConfig;
import com.nanshuo.rpccode.constant.RpcConstant;
import com.nanshuo.rpccode.registry.LocalRegistryCenter;
import com.nanshuo.rpccode.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

import java.rmi.registry.Registry;

/**
 * RPC 框架应用
 * <p>
 * <p>
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/19
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }


    /**
     * 获取配置
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
