package com.abpgroup.majumundur.controller;

import com.abpgroup.majumundur.constant.ApiUrlConstant;
import com.abpgroup.majumundur.model.dto.request.UserRequest;
import com.abpgroup.majumundur.model.dto.response.CommonResponse;
import com.abpgroup.majumundur.model.dto.response.UserResponse;
import com.abpgroup.majumundur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.AUTH)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return ResponseEntity.ok(CommonResponse.<UserResponse>builder().data(userResponse).build());
    }
}
