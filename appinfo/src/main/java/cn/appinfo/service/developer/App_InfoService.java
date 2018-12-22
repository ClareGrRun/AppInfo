package cn.appinfo.service.developer;

import cn.appinfo.pojo.App_info;

import java.util.List;

public interface App_InfoService {
    List<App_info> appinfos(App_info app_info,Integer pageNo,Integer pageSize);

    int appinfoCount(App_info app_info);

    int addAppinfo(App_info app_info);

    App_info info(Integer id);

    int AppinfoCount(String softwareName,String APKName);

    int AppinfoModify(App_info app_info);

    int updateVersionId(Integer id,Integer versionId);

    int delInfo(Integer id);
}
