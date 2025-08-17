package com.xiaoman.openapi.service;

import com.xiaoman.openapi.dal.dataobject.User;

public interface UserService {
    User registerUser(String username, String password) throws Exception;
}
