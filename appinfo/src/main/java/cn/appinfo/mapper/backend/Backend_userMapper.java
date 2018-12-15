package cn.appinfo.mapper.backend;

import cn.appinfo.pojo.Backend_user;

public interface Backend_userMapper {
    Backend_user login(String userCode);
}
