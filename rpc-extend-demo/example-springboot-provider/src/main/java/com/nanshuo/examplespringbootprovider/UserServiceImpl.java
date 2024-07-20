package com.nanshuo.examplespringbootprovider;

import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;
import com.nanshuo.rpcspringbootstarter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("username: " + user.getName());
        return user;
    }
}
