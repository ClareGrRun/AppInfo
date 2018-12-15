package cn.appinfo.service.developer;

import cn.appinfo.pojo.Dev_user;

public interface Dev_userService {
    public Dev_user login(String devCode,String devPassword);
}
