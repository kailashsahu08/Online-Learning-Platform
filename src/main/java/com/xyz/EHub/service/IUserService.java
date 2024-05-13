package com.xyz.EHub.service;

import com.xyz.EHub.exception.UserNotFoundException;
import com.xyz.EHub.model.Users;

public interface IUserService {
    public Users registerUser(Users user);
    public boolean existEmailCheck(String email);
    public Users getUserByUserName(String username) throws Exception, UserNotFoundException;
}
