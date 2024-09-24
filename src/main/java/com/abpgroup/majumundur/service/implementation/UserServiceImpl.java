package com.abpgroup.majumundur.service.implementation;

import com.abpgroup.majumundur.model.dto.request.UserRequest;
import com.abpgroup.majumundur.model.dto.response.UserResponse;
import com.abpgroup.majumundur.model.entity.User;
import com.abpgroup.majumundur.repository.UserRepository;
import com.abpgroup.majumundur.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .role(userRequest.getRole())
                .build();
        User savedUser = userRepository.save(user);
        return convertToUserResponse(savedUser);
    }

    @Override
    public UserResponse getUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return convertToUserResponse(user);
        } catch (Exception e) {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream().map(this::convertToUserResponse).toList();
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest, String username) {
        User user = userRepository.findByUsername(username);
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setRole(userRequest.getRole());
        User savedUser = userRepository.save(user);
        return convertToUserResponse(savedUser);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);

    }

    private UserResponse convertToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
