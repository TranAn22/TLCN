package com.ecommerce.library.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data @NoArgsConstructor @AllArgsConstructor
public class AdminDto {
    @Size(min=1,max=10,message = "Invalid first name! (1-10 characters)")
    private String firstName;
    @Size(min=1,max=10,message = "Invalid last name! (1-10 characters)")
    private String lastName;

    private String username;
    @Size(min=3,max=15,message = "Invalid password! (3-15 characters)")
    private String password;

    private String repeatPassword;

}
