package com.mfbi.services;

import com.mfbi.dto.AuthRequestDTO;
import com.mfbi.dto.AuthResponseDTO;
import com.mfbi.dto.UserDTO;
import com.mfbi.entities.UserEntity;
import com.mfbi.repositories.UserRepository;
import io.vertx.ext.auth.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    UserDTO userDTO;
    @Inject
    PBKDF2Encoder encoder;

    public static UserEntity userEntity;

    @ConfigProperty(name = "com.mfbi.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    public boolean verifyLogin(String username, String password){
        userDTO = userRepository.findByUsername(username);
        if(encoder.encode(password).equals(userDTO.getPassword())){
            return true;
        } else {
            return false;
        }
    }

    public String generateJwt(UserDTO user){
        String token = "";
        try {
            token = TokenUtils.generateToken(user.getUsername(), user.getRoles(), duration,
                    issuer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public String create(UserDTO newUser){
    System.out.println(userEntity);
        userEntity = new UserEntity();
        userEntity.username = newUser.getUsername();
        userEntity.password = encoder.encode(newUser.getPassword());
        userEntity.roles = newUser.getRoles();

        userRepository.persist(userEntity);
        return generateJwt(newUser);
    }
}
