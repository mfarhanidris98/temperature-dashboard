package com.mfbi.resources;

import com.mfbi.dto.ActivationDTO;
import com.mfbi.dto.AuthRequestDTO;
import com.mfbi.dto.UserDTO;
import com.mfbi.entities.ActivationEntity;
import com.mfbi.repositories.ActivationRepository;
import com.mfbi.repositories.UserRepository;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("api/register")
public class RegisterResource {

    @Inject
    Mailer mailer;

    @Inject
    UserRepository userRepository;



    @Transactional
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO userDTO){
        userRepository.create(userDTO);
        return Response.ok().build();
    }


}
