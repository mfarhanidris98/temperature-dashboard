package com.mfbi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class  AuthRequestDTO {

    public String username;
    public String password;

}