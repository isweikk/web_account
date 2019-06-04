package com.mvc.dao;

import com.mvc.pojo.User;
import com.mvc.pojo.UserAuth;

public interface UserExtendMapper{
    User selectUserByName(String name) throws Exception;
    UserAuth selectUserAuthByName(String name) throws Exception;
    UserAuth selectUserAuthByEmail(String email) throws Exception;
    UserAuth selectUserAuthByCellphone(String cellphone) throws Exception;
    Integer insertUserAndGetUid(User record) throws Exception;
    Integer getMaxUid() throws Exception;
}
