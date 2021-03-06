package com.mfbi.resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mfbi.dto.MessageDTO;
import com.mfbi.repositories.UserRepository;

@Path("/role")
public class RoleResource {

    @Inject
    UserRepository userRepository;

    @PermitAll
    @GET
    public long count(){
        return userRepository.count();
    }

    @PermitAll
    @GET @Path("/all") @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(new MessageDTO("Content for public")).build();
    }

    @RolesAllowed("USER")
    @GET @Path("/user") @Produces(MediaType.APPLICATION_JSON)
    public Response user() {
        return Response.ok(new MessageDTO("Content for user")).build();
    }

    @RolesAllowed("ADMIN")
    @GET @Path("/admin") @Produces(MediaType.APPLICATION_JSON)
    public Response admin() {

        return Response.ok(new MessageDTO("Content for admin")).build();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @GET @Path("/user-or-admin") @Produces(MediaType.APPLICATION_JSON)
    public Response userOrAdmin() {
        return Response.ok(new MessageDTO("Content for user or admin")).build();
    }
}