package cn.appinfo.controller.backend;

import cn.appinfo.pojo.App_category;
import cn.appinfo.pojo.App_info;
import cn.appinfo.service.backend.App_checkService;
import cn.appinfo.service.developer.App_CategoryService;
import cn.appinfo.service.developer.Data_dictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("app_check")
public class App_checkController {
    @Autowired
    private App_CategoryService app_categoryService;
    @Autowired
    private Data_dictionaryService data_dictionaryService;
    @Autowired
    private App_checkService app_checkService;

    @RequestMapping("/list")
    public String list(HttpServletRequest request, App_info app_info,String pageNo){
        request.setAttribute("app_flatform",data_dictionaryService.app_flatform());
        request.setAttribute("category01",app_categoryService.category01());
        int currentNo = 1;
        if(pageNo!=null){
            currentNo=Integer.parseInt(pageNo);
        }
        if(currentNo<1){
            currentNo=1;
        }
        int pageSize=5;
        int pageCount = app_checkService.checkCount(app_info);
        int currentPage = pageCount%pageSize==0?pageCount/pageSize:pageCount/pageSize+1;
        if(currentPage>0&&currentNo>currentPage){
            currentNo=currentPage;
        }
        List<App_info> list = app_checkService.checkList(app_info,currentNo,pageSize);
        request.setAttribute("list",list);
        request.setAttribute("currentNo",currentNo);
        request.setAttribute("currentPage",currentPage);
        request.setAttribute("pageCount",pageCount);
        if(app_info.getCategoryLevel1()!=null){
            List<App_category> category2 = app_categoryService.parentCategory(app_info.getCategoryLevel1());
            request.setAttribute("category2",category2);
            if(app_info.getCategoryLevel2()!=null){
                List<App_category> category3 = app_categoryService.parentCategory(app_info.getCategoryLevel2());
                request.setAttribute("category3",category3);
            }
        }
        return "backend/applist";
    }
}
