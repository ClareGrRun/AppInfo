package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.App_categoryMapper;
import cn.appinfo.pojo.App_category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("app_CategoryService")
public class App_CategoryServiceImpl implements App_CategoryService{
    @Autowired
    private App_categoryMapper app_categoryMapper;

    @Override
    public List<App_category> category01() {
        return app_categoryMapper.category01();
    }

    @Override
    public List<App_category> parentCategory(Integer id) {
        return app_categoryMapper.parentCategory(id);
    }
}
