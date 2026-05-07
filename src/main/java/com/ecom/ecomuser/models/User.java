package com.ecom.ecomuser.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

import static com.ecom.ecomuser.models.UserRole.CUSTOMER;


@Data
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User {


    @Id
    private String id;
    private String fName;
    private String lName;

    @Indexed(unique = true)
    private String email;

    private String phoneNumber;
    private UserRole role;

    private Address address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
