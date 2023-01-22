package icu.running.service.impl;

import icu.running.mapper.UserInfoMapper;
import icu.running.pojo.User;
import icu.running.pojo.UserInfo;
import icu.running.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.addUserInfo(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) {
        return userInfoMapper.findById(id);
    }

    @Override
    public int upBalance(UserInfo userInfo) {
        return userInfoMapper.upBalance(userInfo);
    }

    @Override
    public int upUserInfo(UserInfo userInfo) {
        return userInfoMapper.upUserInfo(userInfo);
    }

    @Override
    public int lowerBalance(UserInfo userInfo) {
        return userInfoMapper.lowerBalance(userInfo);
    }

    @Override
    public int delUserInfo(Integer id) {
        return userInfoMapper.delUserInfo(id);
    }

    @Override
    public int updateBl(UserInfo userInfo) {
        return userInfoMapper.updateBl(userInfo);
    }

    @Override
    public int upBigHead(UserInfo userInfo) {
        return userInfoMapper.upBigHead(userInfo);
    }

    @Override
    public User findByEmail(String to) {
        return userInfoMapper.findByEmail(to);
    }

    @Override
    public User findByTel(String tel) {
        return userInfoMapper.findByTel(tel);
    }


}
