package com.nanshuo.rpccode.fault.tolerant;


import com.nanshuo.rpccode.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略（立刻通知外层调用方）
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
public class FailFastTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
