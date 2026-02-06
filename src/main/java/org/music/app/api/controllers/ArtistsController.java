package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
    @Operation(summary = "Cria um Artista", description = "Cria um artista no catalogo")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response create(ArtistsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    @Operation(summary = "Atualiza informações de um artista", description = "Atualiza as informações de um artista do catalogo")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response update(ArtistsResponse request){
        return Response.ok(service.update(request)).build();
    }

    @Path("/{id}")
    @GET
    @Operation(summary = "Lista um artista", description = "Retorna um artista do catálogo")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Operation(summary = "Lista todos artistas", description = "Retorna uma lista completa do catálogo musical de artistas.")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{name}")
    @GET
    public Response findByName(@PathParam("name") String name){
        return Response.ok(service.findByName(name)).build();
    }
}
