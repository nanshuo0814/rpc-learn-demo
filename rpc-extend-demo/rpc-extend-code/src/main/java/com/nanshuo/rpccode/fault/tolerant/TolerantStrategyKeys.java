package com.nanshuo.rpccode.fault.tolerant;

/**
 * 容错策略键名常量
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
public interface TolerantStrategyKeys {

    /**
     * 故障恢复
     */
    String FAIL_BACK = "failBack";

    /**
     * 快速失败
     */
    String FAIL_FAST = "failFast";

    /**
     * 故障转移
     */
    String FAIL_OVER = "failOver";

    /**
     * 静默处理
     */
    String FAIL_SAFE = "failSafe";

}
