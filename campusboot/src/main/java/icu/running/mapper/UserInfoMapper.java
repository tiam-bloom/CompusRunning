package icu.running.mapper;

import icu.running.pojo.User;
import icu.running.pojo.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserInfoMapper {
    int addUserInfo(UserInfo userInfo);

    UserInfo findById(Integer id);

    int upBalance(UserInfo userInfo);

    int upUserInfo(UserInfo userInfo);

    int lowerBalance(UserInfo userInfo);

    int delUserInfo(Integer id);

    int updateBl(UserInfo userInfo);

    @Update("update userinfo set bigHead = #{bigHead} where id = #{id}")
    int upBigHead(UserInfo userInfo);

    User findByEmail(String to);

    User findByTel(String tel);
}
