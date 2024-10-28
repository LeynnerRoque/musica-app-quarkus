package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.music.app.api.dto.request.RecordsRequest;
import org.music.app.api.dto.response.RecordsResponse;
import org.music.app.business.service.RecordService;

@Path("/records")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecordsController {

    private final RecordService service;


    public RecordsController(RecordService service) {
        this.service = service;
    }


    @POST
    public Response create(RecordsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    public Response update(RecordsResponse response){
        return Response.ok(service.update(response)).build();
    }

    @GET
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id")Long id){
        return Response.ok(service.findById(id)).build();
    }
}
