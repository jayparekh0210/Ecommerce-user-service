package com.ecom.ecomuser.dto.requests;


import com.ecom.ecomuser.dto.responses.AddressDTO;
import com.ecom.ecomuser.models.UserRole;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String fName;
    private String lName;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private AddressDTO address;
}
