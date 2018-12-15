package cn.appinfo.mapper.developer;

import cn.appinfo.pojo.Dev_user;
import org.springframework.stereotype.Repository;

public interface Dev_userMapper {
     Dev_user login(String devCode);
}
