package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.App_infoMapper;
import cn.appinfo.pojo.App_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("app_InfoService")
public class App_InfoServiceImpl implements App_InfoService{
    @Autowired
    private App_infoMapper app_infoMapper;

    @Override
    public List<App_info> appinfos(App_info app_info, Integer pageNo,Integer pageSize) {
        pageNo=(pageNo-1)*pageSize;
        return app_infoMapper.appinfos(app_info,pageNo,pageSize);
    }

    @Override
    public int appinfoCount(App_info app_info) {
        return app_infoMapper.appinfoCount(app_info);
    }

    @Override
    public int addAppinfo(App_info app_info) {
        return app_infoMapper.addAppinfo(app_info);
    }

    @Override
    public App_info info(Integer id) {
        return app_infoMapper.info(id);
    }

    @Override
    public int AppinfoCount(String softwareName, String APKName) {
        return app_infoMapper.AppinfoCount(softwareName,APKName);
    }

    @Override
    public int AppinfoModify(App_info app_info) {
        return app_infoMapper.AppinfoModify(app_info);
    }

    @Override
    public int updateVersionId(Integer id, Integer versionId) {
        return app_infoMapper.updateVersionId(id,versionId);
    }

    @Override
    public int delInfo(Integer id) {
        return app_infoMapper.delInfo(id);
    }

    @Override
    public int updateJia(Integer appId, Integer stauts) {
        return app_infoMapper.updateJia(appId,stauts);
    }
}
