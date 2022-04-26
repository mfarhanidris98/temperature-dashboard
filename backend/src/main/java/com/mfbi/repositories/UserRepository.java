package com.mfbi.repositories;

import com.mfbi.dto.UserDTO;
import com.mfbi.entities.UserEntity;
import com.mfbi.services.UserService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {

    @Inject
    UserDTO userDTO;

    @Inject
    UserService userService;

    public UserDTO findByUsername(String username){
        UserEntity userEntity = find("username", username).firstResult();
        userDTO.setUsername(userEntity.username);
        userDTO.setPassword(userEntity.password);
        userDTO.setRoles(userEntity.roles);
        return userDTO;
    }

    public boolean verifyLogin(String username, String password){
        return userService.verifyLogin(username, password);
    }

    public String generateJwt(UserDTO user){
        return userService.generateJwt(user);
    }

    public String create(UserDTO user){
        user.setRoles("USER");
        userService.create(user);
        return userService.generateJwt(user);
    }
}
