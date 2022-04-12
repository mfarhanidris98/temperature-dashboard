package com.mfbi.resources;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mfbi.dto.AuthRequestDTO;
import com.mfbi.dto.AuthResponseDTO;
import com.mfbi.dto.UserDTO;
import com.mfbi.entities.UserEntity;
import com.mfbi.services.PBKDF2Encoder;
import com.mfbi.services.TokenUtils;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/auth")
public class AuthenticationResource {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "com.mfbi.jwt.duration") public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer") public String issuer;

    @PermitAll
    @POST @Path("/login") @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthRequestDTO authRequest) {
        UserDTO u = UserEntity.findByUsername(authRequest.username);
        if (u != null && u.password.equals(passwordEncoder.encode(authRequest.password))) {
            try {
                return Response.ok(new AuthResponseDTO(TokenUtils.generateToken(u.username, u.roles, duration, issuer))).build();
            } catch (Exception e) {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Status.UNAUTHORIZED).build();
        }
    }

}