package com.mfbi.resources;

import java.io.InputStream;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.mfbi.services.CsvService;

@Path("/client")
@ApplicationScoped
public class MultipartClientResource {

    @Inject
    CsvService csvService;

    @Path("/upload")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response upload(InputStream input) {
        System.out.println("Uploading file...");
        return Response.ok(csvService.save(input)).build();
    }

    public boolean checkInput(String str){
        return !str.matches("-+.+")
                && !str.matches("(Content-).+")
                && !str.matches("\"s")
                && !str.matches("(.+)(.csv)");
    }


}