package com.abpgroup.majumundur.service;

import com.abpgroup.majumundur.model.dto.request.UserRequest;
import com.abpgroup.majumundur.model.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse registerUser(UserRequest userRequest);
    UserResponse getUserByUsername(String username);
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UserRequest userRequest, String username);
    void deleteUser(String username);
}
