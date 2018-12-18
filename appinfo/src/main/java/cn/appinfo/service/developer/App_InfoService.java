package cn.appinfo.service.developer;

import cn.appinfo.pojo.App_info;
import org.springframework.stereotype.Service;

import java.util.List;

public interface App_InfoService {
    List<App_info> appinfos(App_info app_info,Integer pageNo,Integer pageSize);

    int appinfoCount(App_info app_info);

}
