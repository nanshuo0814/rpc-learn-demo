package com.nanshuo.rpccode.server;

import com.nanshuo.rpccode.model.RpcRequest;
import com.nanshuo.rpccode.model.RpcResponse;
import com.nanshuo.rpccode.registry.LocalRegistryCenter;
import com.nanshuo.rpccode.serializer.JdkSerializer;
import com.nanshuo.rpccode.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * HTTP 请求处理
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        // 指定序列化器（自定义的JDK序列化器）
        final Serializer serializer = new JdkSerializer();
        // 记录日志
        System.out.println("Received request: " + httpServerRequest.method() + " " + httpServerRequest.uri());
        // 异步处理 HTTP 请求
        httpServerRequest.bodyHandler(body->{
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为NULL，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(httpServerRequest, rpcResponse, serializer);
                return;
            }
            Class<?> clazz = LocalRegistryCenter.get(rpcRequest.getServiceName());
            try {
                Method method = clazz.getMethod(rpcRequest.getMethodName(),rpcRequest.getParameterTypes());
                Object result = method.invoke(clazz.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setMessage("ok");
                rpcResponse.setDataType(method.getReturnType());
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                     InstantiationException e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(httpServerRequest, rpcResponse, serializer);
        });
    }

    /**
     * do响应
     *
     * @param httpServerRequest http服务器请求
     * @param rpcResponse       rpc响应
     * @param serializer        序列化程序
     */
    private void doResponse(HttpServerRequest httpServerRequest, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = httpServerRequest.response().putHeader("content-type", "application/json");
        try {
            byte[] serialize = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialize));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
