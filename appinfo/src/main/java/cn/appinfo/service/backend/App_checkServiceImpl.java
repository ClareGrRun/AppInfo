package cn.appinfo.service.backend;

import cn.appinfo.mapper.backend.App_checkMapper;
import cn.appinfo.pojo.App_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("app_checkService")
@Transactional
public class App_checkServiceImpl implements App_checkService{
    @Autowired
    private App_checkMapper app_checkMapper;

    @Override
    @Transactional(readOnly = true)
    public List<App_info> checkList(App_info app_info, Integer pageNo, Integer pageSize) {
        pageNo=(pageNo-1)*pageSize;
        return app_checkMapper.checkList(app_info,pageNo,pageSize);
    }

    @Override
    @Transactional(readOnly = true)
    public int checkCount(App_info app_info) {
        return app_checkMapper.checkCount(app_info);
    }

    @Override
    public int check(Integer id, Integer status) {
        return app_checkMapper.check(id,status);
    }
}
