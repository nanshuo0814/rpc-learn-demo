package com.nanshuo.examplespringbootconsumer;

import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;
import com.nanshuo.rpcspringbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * 示例服务实现类
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/20
 */
@Service
public class ExampleServiceImpl {

    /**
     * 使用 Rpc 框架注入
     */
    @RpcReference
    private UserService userService;

    /**
     * 测试方法
     */
    public void test() {
        User user = new User();
        user.setName("nanshuo");
        User resultUser = userService.getUser(user);
        System.out.println(resultUser.getName());
    }

}
