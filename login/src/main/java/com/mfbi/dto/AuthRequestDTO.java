package com.mfbi.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @ToString
public class  AuthRequestDTO {

    public String username;
    public String password;

}