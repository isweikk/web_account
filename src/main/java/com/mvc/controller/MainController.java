package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import tools.Constant;
//import tools.StringUtils;

/**
 *
 * @ClassName: MainController
 * @Description:
 * @Description: 首页
 */
@Controller
@RequestMapping("")
public class MainController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @Title: login
     * @return ModelAndView
     * @Description: 跳转到登录
     */
    @RequestMapping(value = "toLogin", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toLogin() throws ServletException, IOException {
        ModelAndView mv =  new ModelAndView("login");
        return mv;
    }
    /**
     * @Title: regist
     * @return ModelAndView
     * @Description: 跳转到注册
     */
    @RequestMapping(value = "toRegist", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toRegister(HttpServletRequest request) throws ServletException, IOException {
        ModelAndView mv =  new ModelAndView("regist");
        return mv;
    }
    /**
     * @Title: profile
     * @return void
     * @Description: 跳转到个人中心
     */
    @RequestMapping(value = "toProfile", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView toProfile(HttpServletRequest request) throws ServletException, IOException {
        //Object msg = RequestContextUtils.getInputFlashMap(request).get("msg");
        ModelAndView mv =  new ModelAndView("profile");
        return mv;
    }

    /**
     * @Title: home
     * @return void
     * @Description: 跳转到主页
     */
    @RequestMapping(value = "toHome", method = {RequestMethod.POST, RequestMethod.GET})
    public void toHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}