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
}
