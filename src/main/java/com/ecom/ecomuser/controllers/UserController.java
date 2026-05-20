package com.ecom.ecomuser.controllers;

import com.ecom.ecomuser.dto.requests.UserRequest;
import com.ecom.ecomuser.dto.responses.UserResponse;
import com.ecom.ecomuser.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {

    UserService userService;
    //Replaced with @Slf4j
    //private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        log.info("Fetching all users");
        return ResponseEntity.status(HttpStatus.OK).body( userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        log.info("Creating user for email: {}", userRequest.getEmail());
        UserResponse user1 = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){
        log.info("Fetching user with id: {}", id);
        Optional<UserResponse> createUserResponse = userService.fetchUser(id);
        return createUserResponse.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable String id, @RequestBody UserRequest updatedUser){
        log.info("Updating user with id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,updatedUser));
    }
}
