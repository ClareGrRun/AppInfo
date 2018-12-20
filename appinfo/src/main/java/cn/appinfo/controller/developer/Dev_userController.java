package cn.appinfo.controller.developer;

import cn.appinfo.pojo.Dev_user;
import cn.appinfo.service.developer.Dev_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dev_user")
public class Dev_userController {
    @Autowired
    private Dev_userService dev_userService;

    /**
     * 开发者登录
     * @param devCode
     * @param devPassword
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String login(String devCode, String devPassword, HttpServletRequest request) {
        Dev_user dev_user = dev_userService.login(devCode, devPassword);
        if (dev_user != null) {
            if (dev_user.getDevPassword().equals(devPassword)) {
                request.getSession().setAttribute("dev_user", dev_user);
                return "developer/main";
            } else {
                request.setAttribute("error","密码错误!");
                return "forward:/devlogin.jsp";
            }
        }else{
            request.setAttribute("error","用户名不存在!");
            return "forward:/devlogin.jsp";
        }
    }

    /**
     * 开发者注销
     * @param request
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/devlogin.jsp";
    }
}
