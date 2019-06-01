package com.mvc.service.impl;

import com.mvc.controller.LoginController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.pojo.*;
import com.mvc.dao.UserExtendMapper;
import com.mvc.service.UserService;

import java.util.Date;


@Service//("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserExtendMapper userExtendMapper;
    private static Logger logger = LogManager.getLogger(LoginController.class.getName());

    public User findUserByUid(int uid) {
        //TODO Auto-generated method stub
        try {
            User user = userExtendMapper.selectByPrimaryKey(uid);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByUname(String userName) {
        try {
            User user = userExtendMapper.selectUserByName(userName);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByEmail(String userEmail) {
        try {
            User user = userExtendMapper.selectUserByEmail(userEmail);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByCellphone(String userCellphone) {
        try {
            User user = userExtendMapper.selectUserByCellphone(userCellphone);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ApiResult addUser(String userType, String name, String password) {
        ApiResult res;
        User user = new User();
        Integer uid = null;
        try {
            uid = userExtendMapper.getMaxUid();
        } catch (Exception err) {
            logger.error("get max uid failed! err=" + err.getMessage());
        }
        String autoname = "u" + (uid.intValue() + 1);
        System.out.println("autoname = " + autoname);
        if (userType.equals("NAME")) {
            user.setUname(name);
            user.setCid(1001);
        } else if (userType.equals("EMAIL")) {
            user.setUname(autoname);
            user.setEmail(name);
            user.setCid(1201);
        } else if (userType.equals("PHONE")) {
            user.setUname(autoname);
            user.setCellphone(name);
            user.setCid(1101);
        } else if (userType.equals("THIRD")) {
            user.setUname(autoname);
            //user.setUname(name);
            user.setCid(1301);
        } else {
            res = new ApiResult(ExceptionEnum.NOERROR);
            return res;
        }
        user.setUpasswd(password);
        user.setRegtime(new Date());
        //insert，判断>0即成功
        try {
            userExtendMapper.insert(user);
        } catch (Exception err) {
            logger.error("Create user failed! err=" + err.getMessage());
            res = new ApiResult(err);
            return res;
        }
        logger.info("Create user ok!");
        res = new ApiResult(ExceptionEnum.NOERROR);
        return res;
    }

    public ApiResult addUserFull(User user) {
        ApiResult res;
        try {
            userExtendMapper.insertSelective(user);
        } catch (Exception err) {
            logger.error("Create user failed! err=" + err.getMessage());
            res = new ApiResult(err);
            return res;
        }
        logger.info("Create user ok!");
        res = new ApiResult(ExceptionEnum.NOERROR);
        return res;
    }

}
