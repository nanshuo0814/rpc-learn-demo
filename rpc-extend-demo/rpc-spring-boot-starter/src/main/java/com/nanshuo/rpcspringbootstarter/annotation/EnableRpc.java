package com.nanshuo.rpcspringbootstarter.annotation;

import com.nanshuo.rpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.nanshuo.rpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.nanshuo.rpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用 Rpc 注解
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 server
     *
     * @return boolean
     */
    boolean needServer() default true;
}