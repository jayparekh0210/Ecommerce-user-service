package com.ecom.ecomuser.services;

import com.ecom.ecomuser.adapters.UserConverter;
import com.ecom.ecomuser.dto.requests.UserRequest;
import com.ecom.ecomuser.dto.responses.UserResponse;
import com.ecom.ecomuser.models.User;
import com.ecom.ecomuser.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private UserRepository userRepository;
    private UserConverter userConverter;

    public List<UserResponse> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::userModelToUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse addUser(UserRequest userRequest) {
        return userConverter.userModelToUserResponse(userRepository.save(userConverter.userRequestToUserModel(userRequest)));
    }

    public Optional<UserResponse> fetchUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return Optional.ofNullable(userConverter.userModelToUserResponse(user));
        } else {
            return Optional.empty();
        }
    }

    public boolean updateUser(Long id, UserRequest userRequest) {
        User updatedUser = userConverter.userRequestToUserModel(userRequest);
        return userRepository.findById(id).map(existinguser -> {
            existinguser.setFName(updatedUser.getFName());
            existinguser.setLName(updatedUser.getLName());
            existinguser.setEmail(updatedUser.getEmail());
            existinguser.setPhoneNumber(updatedUser.getPhoneNumber());
            existinguser.setRole(updatedUser.getRole());
            userRepository.save(existinguser);
            return true;
        }).orElse(false);
    }


}
