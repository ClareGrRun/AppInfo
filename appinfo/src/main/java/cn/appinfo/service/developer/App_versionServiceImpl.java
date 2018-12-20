package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.App_versionMapper;
import cn.appinfo.pojo.App_version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("app_versionService")
public class App_versionServiceImpl implements App_versionService{
    @Autowired
    private App_versionMapper app_versionMapper;
    @Override
    public List<App_version> version(Integer id) {
        return app_versionMapper.version(id);
    }
}
