package cn.appinfo.controller.developer;

import cn.appinfo.pojo.*;
import cn.appinfo.service.developer.App_CategoryService;
import cn.appinfo.service.developer.App_InfoService;
import cn.appinfo.service.developer.App_versionService;
import cn.appinfo.service.developer.Data_dictionaryService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/app_info")
public class App_InfoController {
    @Autowired
    private Data_dictionaryService data_dictionaryService;
    @Autowired
    private App_CategoryService app_categoryService;
    @Autowired
    private App_InfoService app_infoService;
    @Autowired
    private App_versionService app_versionService;

    /**
     * 查询当前用户全部APP信息
     *
     * @param request
     * @param pageNo
     * @param app_info
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, String pageNo, App_info app_info) {
        request.setAttribute("app_status", data_dictionaryService.app_status());
        request.setAttribute("app_flatform", data_dictionaryService.app_flatform());
        request.setAttribute("category01", app_categoryService.category01());
        Dev_user user = (Dev_user) request.getSession().getAttribute("dev_user");
        app_info.setCreatedBy(user.getId());
        int currentNo = 1;
        if (pageNo != null) {
            currentNo = Integer.parseInt(pageNo);
        }
        if (currentNo < 1) {
            currentNo = 1;
        }
        int pageSize = 5;
        int pageCount = app_infoService.appinfoCount(app_info);
        int currentPage = pageCount % pageSize == 0 ? pageCount / pageSize : pageCount / pageSize + 1;
        if (currentPage > 0 && currentNo > currentPage) {
            currentNo = currentPage;
        }
        List<App_info> list = app_infoService.appinfos(app_info, currentNo, pageSize);
        request.setAttribute("list", list);
        request.setAttribute("currentNo", currentNo);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("pageCount", pageCount);
        if (app_info.getCategoryLevel1() != null) {
            List<App_category> category2 = app_categoryService.parentCategory(app_info.getCategoryLevel1());
            request.setAttribute("category2", category2);
            if (app_info.getCategoryLevel2() != null) {
                List<App_category> category3 = app_categoryService.parentCategory(app_info.getCategoryLevel2());
                request.setAttribute("category3", category3);
            }
        }
        return "developer/appinfolist";
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add() {
        return "developer/appinfoadd";
    }

    /**
     * 增加APP信息
     *
     * @param app_info
     * @param attach
     * @param request
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(App_info app_info, MultipartFile attach, HttpServletRequest request) {
        if (app_infoService.AppinfoCount(null, app_info.getAPKName()) > 0) {
            return "developer/appinfoadd";
        }
        boolean flag = true;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("/statics/uploadfiles");
            String oldName = attach.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldName);
            String fileName = UUID.randomUUID() + "." + suffix;
            int filesize = 50 * 1024;
            if (attach.getSize() > filesize) {
                request.setAttribute("fileUploadError", "文件大小不能超过50KB!");
                flag = false;
            } else if (suffix.equalsIgnoreCase("jpg")
                    || suffix.equalsIgnoreCase("jpeg")
                    || suffix.equalsIgnoreCase("png")) {
                File file = new File(path, fileName);
                try {
                    attach.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("fileUploadError", " * 上传失败!");
                    flag = false;
                }
                app_info.setLogoPicPath("/statics/uploadfiles" + "\\" + fileName);
                app_info.setLogoLocPath(path + "\\" + fileName);
            } else {
                request.setAttribute("fileUploadError", " * 上传图片格式不正确!");
                flag = false;
            }
            if (flag) {
                Dev_user user = (Dev_user) request.getSession().getAttribute("dev_user");
                app_info.setCreatedBy(user.getId());
                app_info.setDevId(user.getId());
                if (app_infoService.addAppinfo(app_info) > 0) {
                    return "redirect:/app_info/list";
                } else {
                    return "developer/appinfoadd";
                }
            }
        } else {
            request.setAttribute("fileUploadError", "不是文件类型");
            return "developer/appinfoadd";
        }
        return "developer/appinfoadd";
    }
    /**
     * 跳转修改页面并加载平台信息和APP信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "modify",method = RequestMethod.GET)
    public String modify(Integer id,HttpServletRequest request){
        App_info app_info = app_infoService.info(id);
        request.setAttribute("modifyinfo",app_info);
        request.setAttribute("app_flatform",data_dictionaryService.app_flatform());
        request.setAttribute("category1",app_categoryService.category01());
        if(app_info.getCategoryLevel1()!=null){
            List<App_category> category2 = app_categoryService.parentCategory(app_info.getCategoryLevel1());
            request.setAttribute("category2",category2);
            if(app_info.getCategoryLevel2()!=null){
                List<App_category> category3 = app_categoryService.parentCategory(app_info.getCategoryLevel2());
                request.setAttribute("category3",category3);
            }
        }
        return "developer/appinfomodify";
    }

    /**
     * 修改APP信息
     * @param app_info
     * @param request
     * @return
     */
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public String modify(App_info app_info, HttpServletRequest request,MultipartFile attach) {
        boolean flag = true;
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("/statics/uploadfiles");
            String oldName = attach.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldName);
            String fileName = UUID.randomUUID() + "." + suffix;
            int filesize = 50 * 1024;
            if (attach.getSize() > filesize) {
                request.setAttribute("fileUploadError", "文件大小不能超过50KB!");
                flag = false;
            } else if (suffix.equalsIgnoreCase("jpg")
                    || suffix.equalsIgnoreCase("jpeg")
                    || suffix.equalsIgnoreCase("png")) {
                File file = new File(path, fileName);
                try {
                    attach.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("fileUploadError", " * 上传失败!");
                    flag = false;
                }
            } else {
                request.setAttribute("fileUploadError", " * 上传图片格式不正确!");
                flag = false;
            }
            app_info.setLogoPicPath("/statics/uploadfiles" + "\\" + fileName);
            app_info.setLogoLocPath(path + "\\" + fileName);
        }
        Dev_user user = (Dev_user) request.getSession().getAttribute("dev_user");
        app_info.setModifyBy(user.getId());
        if (flag) {
            if (app_infoService.AppinfoModify(app_info) > 0) {
                return "redirect:/app_info/list";
            } else {
                return "forword:/app_info/modify?id=" + app_info.getId();
            }
        }else{
            return "forword:/app_info/modify?id=" + app_info.getId();
        }

    }

    /**
     * 软件名称和APK名称验证
     * @param softwareName
     * @param APKName
     * @return
     */
    @RequestMapping("yz")
    @ResponseBody
    public Object yz(String softwareName,String APKName){
        if(app_infoService.AppinfoCount(softwareName,APKName)>0){
             return JSON.toJSON("false");
        }else{
            return JSON.toJSON("true");
        }

    }

    /**
     * 查看APP信息及版本信息
     * @param id
     * @param request
     * @param version
     * @return
     */
    @RequestMapping("view")
    public String view(Integer id,Integer version,HttpServletRequest request){
        request.setAttribute("appInfo",app_infoService.info(id));
        request.setAttribute("app_version",app_versionService.version(id,version));
        return "developer/appinfoview";
    }

    /**
     * 删除图片
     * @param loc
     * @param request
     * @return
     */
    @RequestMapping("delPic")
    @ResponseBody
    public Object delPic(String loc,HttpServletRequest request){
        File file = new File(loc);
        file.delete();
        return JSON.toJSON("true");
    }

    /**
     * 动态加载平台信息
     * @return
     */
    @RequestMapping("dictionary")
    @ResponseBody
    public Object dictionart(){
        List<Data_dictionary> dictionaries = data_dictionaryService.app_flatform();
        return dictionaries;
    }

    /**
     * 图片下载
     * @param filename
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("upload")
    public void upload(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        String path = request.getSession().getServletContext().getRealPath(filename);
        if(new File(path).exists()){
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(path)));
            filename = URLEncoder.encode(filename,"UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + filename);
            response.setContentType("multipart/form-data");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            int len = 0;
            while((len = inputStream.read()) != -1){
                out.write(len);
                out.flush();
            }
            out.close();
        }
    }

    @RequestMapping("deleteInfo")
    @ResponseBody
    public Object deleteInfo(Integer infoId,String logoPicPath,HttpServletRequest request){
        List<App_version> versions = app_versionService.version(infoId,null);
            app_versionService.delVersion(infoId);
            for (int i = 0;i<versions.size();i++){
                App_version version = versions.get(i);
                File file = new File(request.getSession().getServletContext().getRealPath("")+version.getDownloadLink());
                file.delete();
            }
            if(app_infoService.delInfo(infoId)>0){
                File file = new File(request.getSession().getServletContext().getRealPath("")+logoPicPath);
                file.delete();
                return JSON.toJSONString(true);
            }else{
                return JSON.toJSONString(false);
        }
    }

    @RequestMapping("jia")
    @ResponseBody
    public Object jia(Integer appId,Integer stauts){
        Map<String,String> map = new HashMap<String,String>();
        map.put("errorCode","0");
        if(app_infoService.updateJia(appId,stauts)>0){
            map.put("resultMsg","success");
        }else{
            map.put("resultMsg","failed");
        }
        return map;
    }
}
