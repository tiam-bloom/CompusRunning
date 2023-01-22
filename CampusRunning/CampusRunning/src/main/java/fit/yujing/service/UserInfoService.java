package fit.yujing.service;

import fit.yujing.pojo.UserInfo;

public interface UserInfoService {

    int addUserInfo(UserInfo userInfo);

    UserInfo findById(Integer id);

    int upBalance(UserInfo userInfo);

    int upUserInfo(UserInfo userInfo);

    int lowerBalance(UserInfo userInfo);

    int delUserInfo(Integer id);

    int updateBl(UserInfo userInfo);
}
