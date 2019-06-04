package com.mvc.service;

import com.mvc.pojo.ApiResult;
import com.mvc.pojo.User;
import com.mvc.pojo.UserAuth;


public interface UserService {
    public User findUserByUid(int uid);
    public User findUserByName(String name);
    public UserAuth findUserAuthByName(String name);
    public UserAuth findUserAuthByEmail(String email);
    public UserAuth findUserAuthByCellphone(String cellphone);

    public ApiResult addUser(String name);
    public ApiResult addUserFull(User user);
    public ApiResult addUserAuth(int suid, String idType, String identifier, String password);
    public ApiResult addUserAuthFull(UserAuth userAuth);
}
