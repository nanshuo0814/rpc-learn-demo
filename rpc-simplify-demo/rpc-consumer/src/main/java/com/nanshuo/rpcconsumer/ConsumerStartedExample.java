package com.nanshuo.rpcconsumer;

import com.nanshuo.rpccommon.model.User;

/**
 * 消费者启动示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class ConsumerStartedExample {

    public static void main(String[] args) {
        // 方式一：静态代理
        UserStaticServiceProxy userService = new UserStaticServiceProxy();
        // 方式二：todo 动态代理
        User user = new User();
        user.setName("nanshuo");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println("username: " + user.getName());
        } else {
            System.out.println("user is null");
        }
    }

}
