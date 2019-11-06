package cn.itcast.client;

import cn.itcast.consumer.pojo.User;

public class UserFeignClientImpl implements UserFeignClient {


    @Override
    public User queryUserById(Long id) {
        User user = new User();
        user.setId(id);
        user.setName("用户查询出现异常！");
        return user;
    }
}
