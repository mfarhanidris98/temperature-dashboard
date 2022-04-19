package com.mfbi.resources;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mfbi.dto.AuthRequestDTO;
import com.mfbi.dto.AuthResponseDTO;
//import com.mfbi.dto.UserDTO;
import com.mfbi.dto.UserDTO;
import com.mfbi.entities.UserEntity;
//import com.mfbi.dto.RoleDTO;
import com.mfbi.repositories.UserRepository;
import com.mfbi.services.PBKDF2Encoder;
import com.mfbi.services.TokenUtils;

import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("/auth")
@ApplicationScoped
public class AuthenticationResource {

    @Inject
    UserRepository userRepository;

    @Inject
    PBKDF2Encoder passwordEncoder;

    public static UserDTO userDTO;
    public static AuthResponseDTO authResponseDTO;


    public static List<UserEntity> userEntities= new ArrayList<>();

    public AuthenticationResource(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER
            = Logger.getLogger(UserRepository.class.getName());


    @GET
    public List<UserEntity> get() {
        return userRepository.listAll(Sort.by("username"));
    }

    @GET
    @Path("/search")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("username") String username){
        return Response.ok(userRepository.findByUsername(username).toString()).build();
    }

    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(UserDTO userDTO){
        if (userRepository.verifyLogin(userDTO.getUsername(), userDTO.getPassword())){
            return Response.ok(userRepository.generateJwt(userDTO)).build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

    @Transactional
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO userDTO){
        userRepository.create(userDTO);
        return Response.ok().build();
    }


//    @PermitAll
//    @POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
//    public Response login(AuthRequestDTO authRequest) {
//
//        UserEntity u = UserEntity.findByUsername(authRequest.username);
//
//        if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
//            try {
//                return Response.ok(new AuthResponseDTO(TokenUtils.generateToken(u.username, u.roles, duration, issuer))).build();
//            } catch (Exception e) {
//                return Response.status(Status.UNAUTHORIZED).build();
//            }
//        } else {
//            return Response.status(Status.UNAUTHORIZED).build();
//        }
//    }

}