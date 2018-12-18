package cn.appinfo.mapper.developer;

import cn.appinfo.pojo.App_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_infoMapper {
    List<App_info> appinfos(@Param("app_info")App_info app_info, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int appinfoCount(App_info app_info);
}
