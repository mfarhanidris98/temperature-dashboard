package com.mfbi.resources;
import com.mfbi.dto.UserDTO;
import com.mfbi.repositories.UserRepository;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("api/register")
public class RegisterResource {

    @Inject
    UserRepository userRepository;



    @Transactional
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(UserDTO userDTO) throws URISyntaxException {
        userRepository.create(userDTO);

        return Response.temporaryRedirect(new URI("http://localhost:3000/login")).status(Response.Status.ACCEPTED).build();
    }


}
