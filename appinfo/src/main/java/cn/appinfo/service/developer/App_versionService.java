package cn.appinfo.service.developer;

import cn.appinfo.pojo.App_version;

import java.util.List;

public interface App_versionService {
    List<App_version> version (Integer id,Integer versionId);

    int addVersion(App_version app_version);

    App_version appVersion(Integer id);

    int modifyVersion(App_version app_version);

    int delVersion(Integer id);
}
