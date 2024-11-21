package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.music.app.api.dto.request.AlbunsRequest;
import org.music.app.api.dto.response.AlbunsResponse;
import org.music.app.business.service.AlbunsService;


@Slf4j
@Path("/albuns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlbunsController {

    private final AlbunsService service;

    public AlbunsController(AlbunsService service) {
        this.service = service;
    }

    @POST
    public Response create(AlbunsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    public Response update(AlbunsResponse request){
        return Response.ok(service.update(request)).build();
    }

    @GET
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @Path("/teste/{id}")
    @GET
    public Response findByIDOtherApplication(@PathParam("id") Long id){
        return Response.ok(service.getByOtherAPI(id)).build();
    }

}
