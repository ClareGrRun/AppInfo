package cn.appinfo.controller.backend;

import cn.appinfo.pojo.App_category;
import cn.appinfo.pojo.App_info;
import cn.appinfo.pojo.App_version;
import cn.appinfo.service.backend.App_checkService;
import cn.appinfo.service.developer.App_CategoryService;
import cn.appinfo.service.developer.App_InfoService;
import cn.appinfo.service.developer.App_versionService;
import cn.appinfo.service.developer.Data_dictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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
    @Autowired
    private App_InfoService app_infoService;
    @Autowired
    private App_versionService app_versionService;

    /**
     * APP审核查询
     * @param request
     * @param app_info
     * @param pageNo
     * @return
     */
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

    @RequestMapping("shenhe")
    public String shenhe(Integer appinfoId,HttpServletRequest request){
        App_info info = app_infoService.info(appinfoId);
        App_version version = app_versionService.appVersion(info.getVersionId());
        request.setAttribute("info",info);
        request.setAttribute("version",version);
        return "backend/appcheck";
    }

    @RequestMapping("upload")
    public void uoload(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getSession().getServletContext().getRealPath(filename);
        if (new File(path).exists()) {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            filename = URLEncoder.encode(filename, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while ((len = inputStream.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
        }
    }

    @RequestMapping("check")
    public String check(App_info appInfo){
        app_checkService.check(appInfo.getId(),appInfo.getStatus());
        return "redirect:/app_check/list";
    }
}
