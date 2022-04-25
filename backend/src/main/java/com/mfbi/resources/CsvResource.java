package com.mfbi.resources;

import com.mfbi.repositories.CsvRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/book")
@ApplicationScoped
public class CsvResource {

    @Inject
    CsvRepository csvRepository;

    @Path("/insert")
    @GET
    public Response insert() {
        boolean bool = csvRepository.save();
        return Response.ok(bool).build();
    }

}
