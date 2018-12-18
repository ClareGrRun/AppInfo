package cn.appinfo.service.developer;

import cn.appinfo.pojo.Data_dictionary;

import java.util.List;

public interface Data_dictionaryService {
    List<Data_dictionary> app_status();

    List<Data_dictionary> app_flatform();
}
