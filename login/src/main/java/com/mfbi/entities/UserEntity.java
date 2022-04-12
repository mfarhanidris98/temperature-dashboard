package com.mfbi.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mfbi.dto.RoleDTO;
import com.mfbi.dto.UserDTO;

public class UserEntity {

    //TODO: load the user from the database PanacheRepository
    public static UserDTO findByUsername(String username) {

        //(extends or PanacheEntity PanacheEntityBase), find("username", username).firstResult();

        Map<String, UserDTO> data = new HashMap<>();

        UserDTO u = new UserDTO("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", Collections.singleton(RoleDTO.Role.USER));

        //username:passwowrd -> user:user
        data.put("user", new UserDTO("user", "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", Collections.singleton(RoleDTO.Role.USER)));

        //username:passwowrd -> admin:admin
        data.put("admin", new UserDTO("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", Collections.singleton(RoleDTO.Role.ADMIN)));

    System.out.println(Collections.singleton(RoleDTO.Role.USER));

        if (data.containsKey(username)) {
            return data.get(username);
        } else {
            return null;
        }
    }
}
