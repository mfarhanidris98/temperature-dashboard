package com.mfbi.resources;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mfbi.entities.MultipartBody;
import com.mfbi.repositories.BookRepository;
import com.mfbi.services.MultipartService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/client")
@ApplicationScoped
public class MultipartClientResource {


    MultipartService service;

    BookRepository bookRepository;

    @POST
    @Path("/multipart")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendFile() throws Exception {
        MultipartBody body = new MultipartBody();
        body.fileName = "greeting.txt";
        body.file = new ByteArrayInputStream("HELLO WORLD".getBytes(StandardCharsets.UTF_8));
        return service.sendMultipartData(body);
    }

    @Path("/upload")
    @POST
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response upload(InputStream input) {
        //TODO: delete strings at start and end
        //TODO: check same uuid
        //TODO: store as one json
        System.out.println("Uploading file...");

        try (Scanner scanner = new Scanner(input)) {
            String idStr = "";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!checkInput(line)) scanner.nextLine();
                else {
//                  System.out.println(line);
                    List<String> rowList = new ArrayList<>(Arrays.asList(line.split(",")));

                    if (idStr.equals("") || !idStr.equals(rowList.get(0))) {
                        System.out.println("From UUID: " + idStr + "\n");
                        idStr = rowList.get(0);
                    }

                    if (idStr.equals(rowList.get(0)) && rowList.size() == 7 && !idStr.equals(
                          "")) {
                    System.out.println("'" + rowList.get(2) + "' : '" + rowList.get(6) + "'");
                  } else if (idStr.equals(rowList.get(0)) && rowList.size() == 6 && !idStr.equals(
                          "")) {
                      System.out.println("'" + rowList.get(2) + "' : '" + rowList.get(5) + "'");
                  }
                }
            }
        }

        return Response.ok().build();
    }

    public boolean checkInput(String str){
        return !str.matches("-+.+")
                && !str.matches("(Content-).+")
                && !str.matches("\"s")
                && !str.matches("(.+)(.csv)");
    }


}