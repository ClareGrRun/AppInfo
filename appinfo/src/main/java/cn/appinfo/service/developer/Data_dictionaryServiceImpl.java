package cn.appinfo.service.developer;

import cn.appinfo.mapper.developer.Data_dictionaryMapper;
import cn.appinfo.pojo.Data_dictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("data_dictionaryService")
public class Data_dictionaryServiceImpl implements Data_dictionaryService{
    @Autowired
    private Data_dictionaryMapper data_dictionaryMapper;

    @Override
    public List<Data_dictionary> app_status() {
        return data_dictionaryMapper.app_status();
    }

    @Override
    public List<Data_dictionary> app_flatform() {
        return data_dictionaryMapper.app_flatform();
    }
}
