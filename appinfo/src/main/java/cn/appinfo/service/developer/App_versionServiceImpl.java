package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.App_versionMapper;
import cn.appinfo.pojo.App_version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("app_versionService")
@Transactional
public class App_versionServiceImpl implements App_versionService{
    @Autowired
    private App_versionMapper app_versionMapper;
    @Override
    @Transactional(readOnly = true)
    public List<App_version> version(Integer id,Integer versionId) {
        return app_versionMapper.version(id,versionId);
    }

    @Override
    public int addVersion(App_version app_version) {
        return app_versionMapper.addVersion(app_version);
    }

    @Override
    @Transactional(readOnly = true)
    public App_version appVersion(Integer id) {
        return app_versionMapper.appVersion(id);
    }

    @Override
    public int modifyVersion(App_version app_version) {
        return app_versionMapper.modifyVersion(app_version);
    }

    @Override
    public int delVersion(Integer id) {
        return app_versionMapper.delVersion(id);
    }
}
