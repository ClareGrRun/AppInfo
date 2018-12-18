package cn.appinfo.service.developer;

import cn.appinfo.pojo.App_category;

import java.util.List;

public interface App_CategoryService {
    List<App_category> category01();

    List<App_category> parentCategory(Integer id);
}
