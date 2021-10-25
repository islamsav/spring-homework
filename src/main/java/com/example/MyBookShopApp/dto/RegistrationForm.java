package com.example.MyBookShopApp.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class RegistrationForm {

    private String name;
    private String email;
    private String phone;
    private String pass;
}
