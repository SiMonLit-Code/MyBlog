package com.czz.service;

import com.czz.po.User;


public interface UserService {

    User checkUser(String username, String password);
}
