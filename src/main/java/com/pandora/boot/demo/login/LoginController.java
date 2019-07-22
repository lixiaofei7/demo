package com.pandora.boot.demo.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
public class LoginController {

    @Value("${my.value}")
    private String val;

    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request){
        return val;
    }

    @RequestMapping("doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request){
        request.getSession().setAttribute("userName",111);
        return "doLogin";
    }

    @RequestMapping("loginout")
    @ResponseBody
    public String loginout(HttpServletRequest request){
        request.getSession().invalidate();
        System.out.println(request.getSession().getAttribute("userName"));
        return "loginout";
    }

    @RequestMapping("index")
    public String index(HttpServletRequest request){

        return "index";
    }

    @RequestMapping("get")
    @ResponseBody
    public String get(HttpServletRequest request){
        System.out.println(request.getSession().getAttribute("userName"));
        return "get";
    }
}
