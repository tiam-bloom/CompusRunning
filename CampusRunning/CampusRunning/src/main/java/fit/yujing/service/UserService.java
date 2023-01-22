package fit.yujing.service;

import fit.yujing.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByName(String username);

    int addUser(User user);

    int upPassword(User user);

    List<User> findAll();

    int upUser(User user);

    int delUser(Integer id);

    List<User> findUserAndInfo1(String name);
}
