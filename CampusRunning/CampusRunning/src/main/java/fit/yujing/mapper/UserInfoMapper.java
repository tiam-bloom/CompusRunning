package fit.yujing.mapper;

import fit.yujing.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    int addUserInfo(UserInfo userInfo);

    UserInfo findById(Integer id);

    int upBalance(UserInfo userInfo);

    int upUserInfo(UserInfo userInfo);

    int lowerBalance(UserInfo userInfo);

    int delUserInfo(Integer id);

    int updateBl(UserInfo userInfo);
}
