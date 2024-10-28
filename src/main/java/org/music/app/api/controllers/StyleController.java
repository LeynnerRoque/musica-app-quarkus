package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.music.app.api.dto.request.StyleRequest;
import org.music.app.api.dto.response.StyleResponse;
import org.music.app.business.service.StyleService;

@Path("/styles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StyleController {

    private final StyleService service;

    public StyleController(StyleService service) {
        this.service = service;
    }

    @POST
    public Response create(StyleRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    public Response update(StyleResponse response){ return  Response.ok(service.update(response)).build();}

    @GET
    public Response listAll(){return Response.ok(service.listAll()).build();}

    @Path("/{id}")
    @GET
    public Response findById(@PathParam("id") Long id){return Response.ok(service.findById(id)).build();}
}
