package cn.appinfo.controller.developer;

import cn.appinfo.pojo.App_version;
import cn.appinfo.pojo.Dev_user;
import cn.appinfo.service.developer.App_InfoService;
import cn.appinfo.service.developer.App_versionService;
import org.apache.commons.io.FilenameUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("app_version")
public class App_versionController {
    @Autowired
    private App_versionService app_versionService;
    @Autowired
    private App_InfoService app_infoService;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Integer id, HttpServletRequest request) {
        List<App_version> versions = app_versionService.version(id, null);
        request.setAttribute("versions", versions);
        request.setAttribute("id", id);
        return "developer/appversionadd";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(App_version app_version, MultipartFile attach, HttpServletRequest request) {
        if (!attach.isEmpty()) {
            String path = request.getSession().getServletContext().getRealPath("/statics/uploadfiles");
            String oldName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldName);
            String fileName = UUID.randomUUID() + "." + prefix;
            if (prefix.equalsIgnoreCase("apk")) {
                if (attach.getSize() < 1024 * 500 * 1024) {
                    File file = new File(path, fileName);
                    try {
                        attach.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Dev_user user = (Dev_user) request.getSession().getAttribute("dev_user");
                    app_version.setCreatedBy(user.getId());
                    app_version.setApkLocPath(path + "/" + fileName);
                    app_version.setDownloadLink("/statics/uploadfiles/" + fileName);
                    app_version.setApkFileName(fileName);
                    if (app_versionService.addVersion(app_version) > 0) {
                        app_infoService.updateVersionId(app_version.getAppId(), app_version.getId());
                        return "redirect:../app_info/list";
                    } else {
                        return "redirect:/app_version/add";
                    }
                }
            }
        }

        return "redirect:/app_version/add";
    }

    @RequestMapping(value = "modify", method = RequestMethod.GET)
    public String modify(Integer versionId, Integer id, HttpServletRequest request) {
        request.setAttribute("versions", app_versionService.version(id, versionId));//APP的历史版本信息
        request.setAttribute("version", app_versionService.appVersion(versionId));//APP的当前版本信息
        return "developer/appversionmodify";
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    public String modify(App_version version, MultipartFile attach,HttpServletRequest request) {
        if(!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("/statics/uploadfiles");
            String oldName = attach.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldName);
            String fileName = UUID.randomUUID() + "." + prefix;
            if (prefix.equalsIgnoreCase("apk")) {
                if (attach.getSize() < 1024 * 500 * 1024) {
                    File file = new File(path, fileName);
                    try {
                        attach.transferTo(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    version.setApkLocPath(path + "/" + fileName);
                    version.setDownloadLink("/statics/uploadfiles/" + fileName);
                    version.setApkFileName(fileName);
                }
            }
        }
        Dev_user user = (Dev_user) request.getSession().getAttribute("dev_user");
        version.setModifyBy(user.getId());
        if(app_versionService.modifyVersion(version)>0){
            return "redirect:/app_info/list";
        }else{
            return "redirect:/app_version/modify";
        }
    }

    @RequestMapping("upload")
    public void upload(String filename, HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    @RequestMapping("del")
    @ResponseBody
    public Object del(String apkFileName,HttpServletRequest request) {
        File file = new File(request.getSession().getServletContext().getRealPath("/statics/uploadfiles")+"//"+apkFileName);
            file.delete();
            return "true";
    }
}
