package icu.running.service;

import icu.running.pojo.User;
import icu.running.pojo.UserInfo;

public interface UserInfoService {

    int addUserInfo(UserInfo userInfo);

    UserInfo findById(Integer id);

    int upBalance(UserInfo userInfo);

    int upUserInfo(UserInfo userInfo);

    int lowerBalance(UserInfo userInfo);

    int delUserInfo(Integer id);

    int updateBl(UserInfo userInfo);

    int upBigHead(UserInfo userInfo);

    User findByEmail(String to);

    User findByTel(String tel);
}
