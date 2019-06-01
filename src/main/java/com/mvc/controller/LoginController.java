package com.mvc.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mvc.pojo.ApiResult;
import com.mvc.pojo.ExceptionEnum;
import org.apache.log4j.*;

import com.mvc.pojo.User;
import com.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import tools.Constant;
import tools.encrypt.VerifyCode;


/**
 *
 * @ClassName: LoginController
 * @Description: 登录controller
 * @Description:跳转到登录界面
 */
@Controller
@RequestMapping("user")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
//@Scope("prototype")
public class LoginController extends HttpServlet {
    @Autowired
    private UserService userService;
    private static Logger logger = LogManager.getLogger(LoginController.class.getName());
    private static final long serialVersionUID = 1L;

    final String nameRule = "^[A-Za-z]\\w{3,16}$";    //名字3-16位，必须字母开头字母数字下划线
    //字母数字下划线
    final String emailRule = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    final String phoneRule = "^[1][34578]\\d{9}$"; //11位数字，第一位=1，第二位=3/4/5/6/8，后9位任意数字
    final String psswdRule = "^\\w{6,16}$";    //密码6-16位，字母数字下划线

    enum UserType{
        NAME, EMAIL, PHONE, THIRD;
    }
    ApiResult res = null;

    /**
     * @Title: login
     * @return String
     * @Description: 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String verifyc  = request.getParameter("verifycode");
        String svc =(String) request.getSession().getAttribute("sessionverify");
        User userInfo = null;

        if(username == null || password == null){
            return new ModelAndView("login","msg", "输入不能为空！");
        }
        if(!svc.equalsIgnoreCase(verifyc)){
            return new ModelAndView("login","msg", "验证码错误！");
        }
        if (username.matches(emailRule)) { //email account
            userInfo = userService.findUserByEmail(username);
        } else if (username.matches(phoneRule)) { //phone account
            userInfo = userService.findUserByCellphone(username);
        } else if (username.matches(nameRule)){ //name account
            userInfo = userService.findUserByUname(username);
        } else {
            return new ModelAndView("login","msg","用户名不规则！");
        }

        if (userInfo == null) {
            return new ModelAndView("login","msg","用户名或密码错误！");
        }

//        logger.error("uid=" + userInfo.getUid());
//        logger.error("name=" + userInfo.getUname());
//        logger.error("cid=" + userInfo.getCid());
//        logger.error("regtime=" + userInfo.getRegtime());
//        logger.error("lognum=" + userInfo.getLognum());
        if(!userInfo.getUpasswd().equals(password)){
            return new ModelAndView("login","msg", "用户名或密码错误！");
        }
        writeToSession(userInfo, request);
        String msg = "用户："+username+",欢迎访问";
        attr.addFlashAttribute("msg", msg);
        return new ModelAndView("home", "msg", msg);
    }

    /**
     * @Title: logout
     * @param @return
     * @return String
     * @Description: 注销
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
        //去掉用户信息
        request.getSession().removeAttribute(Constant.USER);
        return "redirect:/toHome";
    }

    /**
     * @Title: regist
     * @return String
     * @Description: 注册
     */
    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public ModelAndView regist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userType = request.getParameter("userType");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String verifyc  = request.getParameter("verifycode");
        String svc =(String) request.getSession().getAttribute("sessionverify");
        User u = null;

        if(!svc.equalsIgnoreCase(verifyc)){
            return new ModelAndView("regist","msg", "验证码错误！");
        }
        if(userType == null || username == null || password == null || rePassword == null){
            return new ModelAndView("regist","msg", "输入不能为空！");
        }
        if (userType.equals(UserType.NAME.toString())) {    //name account
            if(!username.matches(nameRule)){
                return new ModelAndView("regist","msg", "名称不符合规则！");
            }
            u = userService.findUserByUname(username);
        } else if (userType.equals(UserType.EMAIL.toString())) {    //email account
            if(!username.matches(emailRule)){
                return new ModelAndView("regist","msg", "邮箱不符合规则！");
            }
            u = userService.findUserByEmail(username);
        } else if (userType.equals(UserType.PHONE.toString())) {    //phone account
            if(!username.matches(phoneRule)){
                return new ModelAndView("regist","msg", "手机不符合规则！");
            }
            u = userService.findUserByCellphone(username);
        } else if (userType.equals(UserType.THIRD.toString())) {    //third account
            return new ModelAndView("regist","msg", "不支持第三方登录！");
        } else {
            return new ModelAndView("regist","msg", "账号不支持");
        }

        if (u != null) {
            return new ModelAndView("regist","msg", "用户已注册！");
        }

        if(!password.matches(psswdRule)){
            return new ModelAndView("regist","msg", "密码不符合规则！");
        }
        if(!password.equals(rePassword)){
            return new ModelAndView("regist","msg", "两次输入的密码不同！");
        }

        res = userService.addUser(userType, username, password);
        if (res.getErrorCode() != ExceptionEnum.NOERROR.getCode()) {
            return new ModelAndView("regist","msg",  "注册失败：请重试！");
        }
        String msg = "恭喜：" + username + "，注册成功";
        return new ModelAndView("regist","msg", msg);
    }

    /**
     *
     * @Title: profile
     * @param @return
     * @return String
     * @Description: 个人中心
     */
    @RequestMapping(value = "profile", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView profile(HttpServletRequest request)
            throws IOException{
        Object msg = RequestContextUtils.getInputFlashMap(request).get("msg");
        return new ModelAndView("profile", "msg", msg);
    }

    /**
     *
     * @Title: writeToSession
     * @param @param user
     * @return boolean
     * @Description:把用户信息写入session
     */
    private boolean writeToSession(User user, HttpServletRequest  request){
        if (user != null) {
            request.getSession().setAttribute(Constant.USER, user);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "getVerifyCode", method = {RequestMethod.POST, RequestMethod.GET})
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage(85,23);//设置验证码图片大小
        request.getSession().setAttribute("sessionverify", vc.getText());//将验证码文本保存到session域
        VerifyCode.output(image, response.getOutputStream());
    }
    /**
     * 使用Pattern和Matcher类的方法
     * @param s
     * @return
     */
    public static boolean isNumber(String s){

        String regex = "^[1-9][0-9]*\\.[0-9]+$|^[1-9][0-9]*$|^0+\\.[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        char c = s.charAt(0);
        if(c=='+'||c=='-'){
            s = s.substring(1);
        }
        Matcher matcher = pattern.matcher(s);
        boolean bool = matcher.matches();
        if(bool){
            return true;
        }else{
            return false;
        }
    }


    //@RequestMapping(value = "login", method = RequestMethod.POST)
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
//
//        request.setAttribute("msg", "ceshi 中文编码！");
//        request.getRequestDispatcher("/logined.jsp").forward(request, response);
//        return;
//    }
}