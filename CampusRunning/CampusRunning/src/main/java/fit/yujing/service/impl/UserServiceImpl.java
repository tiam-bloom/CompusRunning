package fit.yujing.service.impl;

import fit.yujing.mapper.UserMapper;
import fit.yujing.pojo.User;
import fit.yujing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int upPassword(User user) {
        return userMapper.upPassword(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int upUser(User user) {
        return userMapper.upUser(user);
    }

    @Override
    public int delUser(Integer id) {
        return userMapper.delUser(id);
    }

    @Override
    public List<User> findUserAndInfo1(String name) {
        return userMapper.findUserAndInfo1(name);
    }
}
