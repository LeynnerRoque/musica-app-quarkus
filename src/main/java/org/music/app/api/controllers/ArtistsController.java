package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.response.ArtistsResponse;
import org.music.app.business.service.ArtistsService;

@Path("/artists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistsController {

    private final ArtistsService service;

    public ArtistsController(ArtistsService service) {
        this.service = service;
    }

    @POST
    public Response create(ArtistsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    public Response update(ArtistsResponse request){
        return Response.ok(service.update(request)).build();
    }

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{name}")
    @GET
    public Response findByName(@PathParam("name") String name){
        return Response.ok(service.findByName(name)).build();
    }
}
