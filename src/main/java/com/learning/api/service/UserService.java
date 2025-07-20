package com.learning.api.service;

import com.learning.api.dto.UserRequest;
import com.learning.api.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUser(String id);

    List<UserResponse> getUsers();

    UserResponse updateUser(String id, UserRequest request);

    boolean deleteUser(String id);
}
