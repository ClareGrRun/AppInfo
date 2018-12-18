package cn.appinfo.mapper.developer;

import cn.appinfo.pojo.App_category;

import java.util.List;

public interface App_categoryMapper {
    List<App_category> category01();

    List<App_category> parentCategory(Integer id);
}
