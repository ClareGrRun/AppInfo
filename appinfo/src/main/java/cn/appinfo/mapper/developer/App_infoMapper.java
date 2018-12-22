package cn.appinfo.mapper.developer;

import cn.appinfo.pojo.App_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_infoMapper {
    List<App_info> appinfos(@Param("app_info")App_info app_info, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int appinfoCount(App_info app_info);

    int addAppinfo(App_info app_info);

    App_info info(Integer id);

    int AppinfoCount(@Param("softwareName") String softwareName,@Param("APKName") String APKName);

    int AppinfoModify(App_info app_info);

    int updateVersionId(@Param("id") Integer id,@Param("versionId") Integer versionId);

    int delInfo(Integer id);
}
