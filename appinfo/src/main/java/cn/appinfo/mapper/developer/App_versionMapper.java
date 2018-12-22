package cn.appinfo.mapper.developer;

import cn.appinfo.pojo.App_version;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_versionMapper {
    List<App_version> version (@Param("id") Integer id,@Param("versionId") Integer versionId);

    int addVersion(App_version app_version);

    App_version appVersion(Integer id);

    int modifyVersion(App_version app_version);

    int delVersion(Integer id);
}
