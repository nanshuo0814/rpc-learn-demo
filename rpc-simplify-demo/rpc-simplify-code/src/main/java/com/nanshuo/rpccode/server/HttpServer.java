package com.nanshuo.rpccode.server;

/**
 * http服务器
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public interface HttpServer {

    /**
     * 启动服务器
     *
     * @param port 端口
     */
    void doStart(int port);
}
