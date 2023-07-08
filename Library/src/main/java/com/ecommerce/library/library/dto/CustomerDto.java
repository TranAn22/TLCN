package com.ecommerce.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @Size(min = 1, max = 15, message = "First name should have 1-15 characters")
    private String firstName;

    @Size(min = 1, max = 15, message = "Last name should have 1-15 characters")
    private String lastName;

    private String username;

    @Size(min = 5, max = 20, message = "Password should have 5-20 characters")
    private String password;

    private String repeatPassword;
}