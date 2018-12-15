package cn.appinfo.service.backend;

import cn.appinfo.mapper.backend.Backend_userMapper;
import cn.appinfo.pojo.Backend_user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("backend_userService")
public class Backend_userServiceImpl implements Backend_userService{
    @Autowired
    private Backend_userMapper backend_userMapper;
    @Override
    public Backend_user login(String userCode) {
        return backend_userMapper.login(userCode);
    }
}
