package cn.itcast.user.Service;

import cn.itcast.user.mapper.UserMapper;
import cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    public User queryById(Long id){
        return  userMapper.selectByPrimaryKey(id);
    }
}
