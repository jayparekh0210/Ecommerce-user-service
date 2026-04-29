package com.ecom.ecomuser.adapters;


import com.ecom.ecomuser.dto.requests.CreateUserRequest;
import com.ecom.ecomuser.dto.responses.CreateUserResponse;
import com.ecom.ecomuser.models.User;
import com.ecom.ecomuser.models.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserConverter {

    private AddressConverter addressConverter;

    public CreateUserResponse userModelToUserResponse(User user) {
        return CreateUserResponse.builder()
                .id(String.valueOf(user.getId()))
                .fName(user.getFName())
                .lName(user.getLName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .address(user.getAddress() != null
                        ? addressConverter.addressModelToAddressResponse(user.getAddress())
                        : null)
                .build();
    }

    public User userRequestToUserModel(CreateUserRequest userRequest){
        return User.builder()
                .fName(userRequest.getFName())
                .lName(userRequest.getLName())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .role(userRequest.getRole() != null ? userRequest.getRole() : UserRole.CUSTOMER)
                .address(userRequest.getAddress() != null
                        ? addressConverter.addressDTOToAddressModel(userRequest.getAddress())
                        : null)
                .build();
    }


}
