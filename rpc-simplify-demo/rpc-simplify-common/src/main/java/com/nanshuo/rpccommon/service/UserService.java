package com.nanshuo.rpccommon.service;

import com.nanshuo.rpccommon.model.User;

/**
 * 用户服务
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link User }
     */
    User getUser(User user);

}
