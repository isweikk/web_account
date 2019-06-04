package com.mvc.service.impl;

import com.mvc.controller.LoginController;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.pojo.*;
import com.mvc.dao.UserMapper;
import com.mvc.dao.UserAuthMapper;
import com.mvc.dao.UserExtendMapper;
import com.mvc.service.UserService;

import java.text.DateFormat;
import java.util.Date;


@Service//("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthMapper userAuthMapper;
    @Autowired
    private UserExtendMapper userExtendMapper;
    private static Logger logger = LogManager.getLogger(LoginController.class.getName());

    public User findUserByUid(int uid) {
        //TODO Auto-generated method stub
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findUserByName(String name) {
        try {
            User user = userExtendMapper.selectUserByName(name);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserAuth findUserAuthByName(String name) {
        try {
            UserAuth userAuth = userExtendMapper.selectUserAuthByName(name);
            return userAuth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserAuth findUserAuthByEmail(String email) {
        try {
            UserAuth userAuth = userExtendMapper.selectUserAuthByEmail(email);
            return userAuth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserAuth findUserAuthByCellphone(String cellphone) {
        try {
            UserAuth userAuth = userExtendMapper.selectUserAuthByCellphone(cellphone);
            return userAuth;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public ApiResult addUserFull(String userType, String name, String password) {
//        ApiResult res;
//        User user = new User();
//        Integer uid = null;
//        try {
//            uid = userExtendMapper.getMaxUid();
//        } catch (Exception err) {
//            logger.error("get max uid failed! err=" + err.getMessage());
//        }
//        String autoname = "u" + (uid.intValue() + 1);
//        System.out.println("autoname = " + autoname);
//        if (userType.equals("NAME")) {
//            user.setUname(name);
//            user.setCid(1001);
//        } else if (userType.equals("EMAIL")) {
//            user.setUname(autoname);
//            user.setEmail(name);
//            user.setCid(1201);
//        } else if (userType.equals("PHONE")) {
//            user.setUname(autoname);
//            user.setCellphone(name);
//            user.setCid(1101);
//        } else if (userType.equals("THIRD")) {
//            user.setUname(autoname);
//            //user.setUname(name);
//            user.setCid(1301);
//        } else {
//            res = new ApiResult(ExceptionEnum.NOERROR);
//            return res;
//        }
//        user.setUpasswd(password);
//        user.setRegtime(new Date());
//        //insert，判断>0即成功
//        try {
//            userExtendMapper.insert(user);
//        } catch (Exception err) {
//            logger.error("Create user failed! err=" + err.getMessage());
//            res = new ApiResult(err);
//            return res;
//        }
//        logger.info("Create user ok!");
//        res = new ApiResult(ExceptionEnum.NOERROR);
//        return res;
//    }

    public ApiResult addUser(String name) {
        if (name == null) {
            return new ApiResult(ExceptionEnum.UNKNOWN);
        }
        User user = new User();
        user.setNickname(name);
        return addUserFull(user);
    }

    public ApiResult addUserFull(User user) {
        if (user.getNickname() == null) {
            return new ApiResult(ExceptionEnum.UNKNOWN);
        }
        if (user.getCid() == null || user.getCid().intValue() <= 0) {
            user.setCid(new Integer(1001));
        }
        user.setRegtime(new Date());
        if (user.getRank() == null || user.getRank().intValue() <= 0) {
            user.setRank(new Byte((byte)1));
        }
        if (user.getStatus() == null) {
            user.setStatus("INACTIVE");
        }
        if (user.getBirthday() == null) {
            user.setBirthday(user.getRegtime());
        }
        if (user.getGender() == null) {
            user.setGender("FEMALE");
        }
        try {
            Integer uid = userExtendMapper.insertUserAndGetUid(user);
            logger.info("new uid = " + uid);
            user.setUid(uid);
        } catch (Exception err) {
            logger.error("Create user failed! err=" + err.getMessage());
            return new ApiResult(err);
        }
        logger.info("Create user ok!");
        return new ApiResult(ExceptionEnum.NOERROR);
    }

    public ApiResult addUserAuth(int suid, String idType, String identifier, String password) {
        UserAuth userAuth = new UserAuth();

        userAuth.setSuid(suid);
        userAuth.setIdType(idType);
        userAuth.setIdentifier(identifier);
        userAuth.setPassword(password);
        return addUserAuthFull(userAuth);
    }

    public ApiResult addUserAuthFull(UserAuth userAuth) {
        if (userAuth.getSuid() == null
                || userAuth.getSuid() == null
                || userAuth.getIdType() == null
                || userAuth.getIdentifier() == null
                || userAuth.getPassword() == null) {
            return new ApiResult(ExceptionEnum.UNKNOWN);
        }

        userAuth.setBindTime(new Date());
        userAuth.setLoginTime(userAuth.getBindTime());
        userAuth.setLognum(1L);
        try {
            userAuthMapper.insertSelective(userAuth);
        } catch (Exception err) {
            logger.error("Create user auth failed! err=" + err.getMessage());
            return new ApiResult(err);
        }
        logger.info("Create user auth ok!");
        return new ApiResult(ExceptionEnum.NOERROR);
    }

}
