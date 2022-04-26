package com.mfbi.resources;

import com.mfbi.dto.ActivationDTO;
import com.mfbi.entities.ActivationEntity;
import com.mfbi.repositories.ActivationRepository;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Path("api/activation")
@ApplicationScoped
public class ActivationResource {

    @Inject
    Mailer mailer;

    @Path("/sendEmail")
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void sendEmail(ActivationDTO activationDTO) throws URISyntaxException {
        UUID uuid = UUID.randomUUID();
        ActivationRepository activationRepository = new ActivationRepository();
        ActivationEntity activationEntity = new ActivationEntity();
        activationEntity.username = activationDTO.getUsername();
        activationEntity.key = uuid.toString();

        if(activationRepository.isPersistent(activationEntity))
            activationRepository.deleteByUsername(activationEntity.username);

        activationRepository.persist(activationEntity);

        mailer.send(
                Mail.withText(activationDTO.getUsername(),
                        "Dashboard Account Activation",
                        "Please continue your registration here: \n" + "http://localhost:8080" +
                                "/api/activation/authKey?username=" + activationDTO.getUsername() + "&authKey" +
                                "=" + uuid
                )
        );

    }

    @Path("authKey")
    @GET
    @Transactional
    public Response authenticateKey(@QueryParam("username") String username,
                                @QueryParam("authKey") String authKey) throws URISyntaxException {
        ActivationDTO activationDTO = new ActivationDTO(username, authKey);
        ActivationRepository activationRepository = new ActivationRepository();
        if (activationRepository.verifyKey(activationDTO)){
            activationRepository.deleteByUsername(username);
            return Response.temporaryRedirect(new URI("http://localhost:3000/register")).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
