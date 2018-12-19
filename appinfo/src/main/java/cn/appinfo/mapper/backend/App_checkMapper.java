package cn.appinfo.mapper.backend;

import cn.appinfo.pojo.App_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface App_checkMapper {
    List<App_info> checkList(@Param("app_info") App_info app_info,@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize);

    int checkCount(App_info app_info);
}
