package com.mfbi.dto;

import lombok.*;

import javax.enterprise.context.RequestScoped;
import java.util.Set;

@Data
@RequestScoped
public class UserDTO {

    public String username;
    public String password;
    public String roles;

}
