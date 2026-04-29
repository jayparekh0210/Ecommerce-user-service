package com.ecom.ecomuser.controllers;

import com.ecom.ecomuser.dto.requests.UserRequest;
import com.ecom.ecomuser.dto.responses.UserResponse;
import com.ecom.ecomuser.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponse>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body( userService.getAllUser());
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        UserResponse user1 = userService.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        Optional<UserResponse> createUserResponse = userService.fetchUser(id);
        return createUserResponse.map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUser){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,updatedUser));
    }
}
