package com.mfbi.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.mfbi.dto.AuthResponseDTO;
import com.mfbi.dto.UserDTO;
import com.mfbi.repositories.UserRepository;

@Path("/api/auth/")
@ApplicationScoped
public class AuthenticationResource {

    @Inject
    UserRepository userRepository;

    public AuthenticationResource(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO){
        if (userRepository.verifyLogin(userDTO.getUsername(), userDTO.getPassword())){
            userDTO = userRepository.findByUsername(userDTO.getUsername());
            AuthResponseDTO au =
            new AuthResponseDTO(
              userRepository.generateJwt(userDTO), userDTO.getRoles(), userDTO.getUsername(),
                  userDTO.getUsername());
            return Response.ok(au).build();
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

}