package cn.appinfo.service.backend;

import cn.appinfo.pojo.Backend_user;

public interface Backend_userService {
    Backend_user login(String userCode);
}
