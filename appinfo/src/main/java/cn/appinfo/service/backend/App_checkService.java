package cn.appinfo.service.backend;

import cn.appinfo.pojo.App_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_checkService {
    List<App_info> checkList(@Param("appinfo") App_info app_info, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    int checkCount(App_info app_info);
}
