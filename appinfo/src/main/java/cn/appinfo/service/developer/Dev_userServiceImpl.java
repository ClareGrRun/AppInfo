package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.Dev_userMapper;
import cn.appinfo.pojo.Dev_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("dev_userService")
@Transactional
public class Dev_userServiceImpl implements Dev_userService {
    @Autowired
    private Dev_userMapper dev_userMapper;

    @Override
    public Dev_user login(String devCode,String devPassword) {
        return dev_userMapper.login(devCode);
    }
}
