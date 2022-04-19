package com.mfbi.resources;

import com.mfbi.entities.UserEntity;
import com.mfbi.repositories.BookRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/book")
@ApplicationScoped
public class BookResource {

    @Inject
    BookRepository bookRepository;

    @Path("/insert")
    @GET
    public Response insert() {
        boolean bool = bookRepository.save();


        return Response.ok(bool).build();

    }

}
