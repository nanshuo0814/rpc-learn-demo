package com.nanshuo.rpccomsumer;


import com.nanshuo.rpccode.proxy.ServiceDynamicProxyFactory;
import com.nanshuo.rpccommon.model.User;
import com.nanshuo.rpccommon.service.UserService;

/**
 * 消费者启动示例
 *
 * @author <a href="https://github.com/nanshuo0814">nanshuo(南烁)</a>
 * @date 2024/07/18
 */
public class ConsumerStartedExample {

    public static void main(String[] args) {
        // 方式一：静态代理
        //UserStaticServiceProxy userService = new UserStaticServiceProxy();
        // 方式二：动态代理
        UserService userService = ServiceDynamicProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("nanshuo");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println("username: " + user.getName());
        } else {
            System.out.println("user is null");
        }
        //short number = userService.getNumber();
        //System.out.println(number);
    }

}
