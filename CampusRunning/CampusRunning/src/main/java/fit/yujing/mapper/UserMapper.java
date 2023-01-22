package fit.yujing.mapper;

import fit.yujing.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findUserByName(String username);

    int addUser(User user);

    int upPassword(User user);

    List<User> findAll();

    int upUser(User user);

    int delUser(Integer id);

    List<User> findUserAndInfo1(String name);


}
