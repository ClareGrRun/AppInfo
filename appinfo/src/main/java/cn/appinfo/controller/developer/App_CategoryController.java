package cn.appinfo.controller.developer;

import cn.appinfo.pojo.App_category;
import cn.appinfo.service.developer.App_CategoryService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app_category")
public class App_CategoryController {
    @Autowired
    private App_CategoryService app_categoryService;

    @RequestMapping("category")
    @ResponseBody
    public Object category(String id) {
        List<App_category> category = app_categoryService.parentCategory(Integer.parseInt(id));
        return category;
    }
}
