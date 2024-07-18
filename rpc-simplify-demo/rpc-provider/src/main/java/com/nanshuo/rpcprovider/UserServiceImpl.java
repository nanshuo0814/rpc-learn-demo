package com.nanshuo.rpcprovider;

import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;

/**
 * 用户服务实施
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class UserServiceImpl implements UserService {

    /**
     * 获取用户
     *
     * @param user 用户
     * @return {@link User }
     */
    @Override
    public User getUser(User user) {
        System.out.println("username: " + user.getName());
        return user;
    }
}
