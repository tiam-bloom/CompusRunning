package fit.yujing.service.impl;

import fit.yujing.mapper.UserInfoMapper;
import fit.yujing.pojo.UserInfo;
import fit.yujing.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
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


}
