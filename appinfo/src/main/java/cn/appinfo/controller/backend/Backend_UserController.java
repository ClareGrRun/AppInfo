package cn.appinfo.controller.backend;

import cn.appinfo.pojo.Backend_user;
import cn.appinfo.service.backend.Backend_userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/backend_user")
public class Backend_UserController {
    @Autowired
    private Backend_userService backend_userService;

    /**
     * 后台管理员登录
     * @param userCode
     * @param userPassword
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(String userCode, String userPassword, HttpServletRequest request){
        Backend_user backend_user = backend_userService.login(userCode);
        if(backend_user!=null){
            if(backend_user.getUserPassword().equals(userPassword)){
                request.getSession().setAttribute("backend_user",backend_user);
                return "backend/main";
            }else{
                request.setAttribute("error","密码错误!");
                return "forward:/backendlogin.jsp";
            }
        }else{
            request.setAttribute("error","用户名不存在!");
            return "forward:/backendlogin.jsp";
        }
    }

    /**
     * 后台管理员注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/backendlogin.jsp";
    }
}
