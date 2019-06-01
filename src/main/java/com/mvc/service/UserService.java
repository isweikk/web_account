package com.mvc.service;

import com.mvc.pojo.ApiResult;
import com.mvc.pojo.User;


public interface UserService {
    public User findUserByUid(int uid);
    public User findUserByUname(String userName);
    public User findUserByEmail(String userEmail);
    public User findUserByCellphone(String userCellphone);

    public ApiResult addUser(String userType, String name, String password);
    public ApiResult addUserFull(User user);
}
