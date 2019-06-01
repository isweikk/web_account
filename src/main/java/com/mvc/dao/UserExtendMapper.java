package com.mvc.dao;

import com.mvc.pojo.User;

public interface UserExtendMapper extends UserMapper {
    User selectUserByName(String uname) throws Exception;
    User selectUserByEmail(String email) throws Exception;
    User selectUserByCellphone(String cellphone) throws Exception;
    Integer insertAndGetKey(User record) throws Exception;
    Integer getMaxUid() throws Exception;
}
