package com.mfbi.dto;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Set;

@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDTO {

    public String username;
    public String password;
    public Set<RoleDTO> roles;

    public <T> UserDTO(String user, String s, Set<T> singleton) {
    }
}
