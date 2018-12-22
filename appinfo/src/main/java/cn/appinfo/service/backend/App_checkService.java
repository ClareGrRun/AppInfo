package cn.appinfo.service.backend;

import cn.appinfo.pojo.App_info;

import java.util.List;

public interface App_checkService {
    List<App_info> checkList(App_info app_info,Integer pageNo,Integer pageSize);

    int checkCount(App_info app_info);

    int check(Integer id,Integer status);
}
