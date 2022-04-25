package com.mfbi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class  AuthRequestDTO {

    public String username;
    public String password;
    public String authKey;

}