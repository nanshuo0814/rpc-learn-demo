package com.nanshuo.rpccode.fault.tolerant;

import com.nanshuo.rpccode.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


/**
 * 降级到其他服务 - 容错策略
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
@Slf4j
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo 可自行扩展，获取降级的服务并调用
        return null;
    }
}
