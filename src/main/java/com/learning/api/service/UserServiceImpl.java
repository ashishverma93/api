package com.learning.api.service;

import com.learning.api.dto.UserRequest;
import com.learning.api.dto.UserResponse;
import com.learning.api.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();

    @Override
    public UserResponse createUser(UserRequest request) {
        User newUser = mapToUser(request);
        users.add(newUser);
        return mapToUserResponse(newUser);
    }

    @Override
    public UserResponse getUser(String id) {

        User user = users.stream().filter(u -> u.getId().equalsIgnoreCase(id)).findFirst().orElse(null);

//        User user = null;
//        for (User u : users) {
//            if (u.getId().equalsIgnoreCase(id)) {
//                user = u;
//                break;
//            }
//        }
        return user != null ? mapToUserResponse(user) : null;
//        UserResponse response = null;
//        if (user != null) {
//            response = mapToUserResponse(user);
//        }
//        return response;
    }

    @Override
    public List<UserResponse> getUsers() {
        return users.stream().map(this::mapToUserResponse).toList();
    }

    @Override
    public UserResponse updateUser(String id, UserRequest request) {
        User existingUser = users.stream().filter(u -> u.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
//        User existingUser = null;
//        for (User u : users) {
//            if (u.getId().equalsIgnoreCase(id)) {
//                existingUser = u;
//                break;
//            }
//        }
        if (existingUser == null) {
            return null;
        }
        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setDepartment(request.getDepartment());
        return mapToUserResponse(existingUser);
    }

    @Override
    public boolean deleteUser(String id) {
        return users.stream()
                .filter(u -> u.getId().equalsIgnoreCase(id))
                .findFirst()
                .map(eu -> {
                    eu.setStatus(false);
                    return true;
                }).orElse(false);
//        for (User user : users) {
//            if (user.getId().equalsIgnoreCase(id)) {
//                user.setStatus(false);
//                return true;
//            }
//        }
//        return false;
    }

    private User mapToUser(UserRequest request) {
        return new User(UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getDepartment(),
                LocalDateTime.now(),
                true);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getDepartment(), user.getCreatedAt());
    }
}
