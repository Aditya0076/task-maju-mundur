package com.abpgroup.majumundur.controller;

import com.abpgroup.majumundur.constant.ApiUrlConstant;
import com.abpgroup.majumundur.model.dto.request.UserRequest;
import com.abpgroup.majumundur.model.dto.response.CommonResponse;
import com.abpgroup.majumundur.model.dto.response.UserResponse;
import com.abpgroup.majumundur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.AUTH)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<CommonResponse<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.registerUser(userRequest);
        return ResponseEntity.ok(CommonResponse.<UserResponse>builder()
                .data(userResponse)
                .message("User created successfully")
                .status(200)
                .build());
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<CommonResponse<UserResponse>> updateUser(@RequestBody UserRequest userRequest, @PathVariable String username) {
        UserResponse userResponse = userService.updateUser(userRequest, username);
        return ResponseEntity.ok(CommonResponse.<UserResponse>builder()
                .data(userResponse)
                .message("User updated successfully")
                .status(200)
                .build());
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<CommonResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        UserResponse userResponse = userService.getUserByUsername(username);
        return ResponseEntity.ok(CommonResponse.<UserResponse>builder()
                .data(userResponse)
                .message("User retrieved successfully")
                .status(200)
                .build());
    }
    @GetMapping("/all")
    public ResponseEntity<CommonResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(CommonResponse.<List<UserResponse>>builder()
                .data(userResponses)
                .message("Users retrieved successfully")
                .status(200)
                .build());
    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<CommonResponse<Void>> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok(CommonResponse.<Void>builder()
                .message("User deleted successfully")
                .status(200)
                .build());
    }
}
