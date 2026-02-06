package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
    @Operation(summary = "Cria um Album", description = "Cria uma album")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response create(AlbunsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    @Operation(summary = "Atualiza um Album", description = "Atualiza as informações do Album")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response update(AlbunsResponse request){
        return Response.ok(service.update(request)).build();
    }

    @GET
    @Operation(summary = "Lista todas os Albuns", description = "Retorna uma lista completa do catálogo de Albuns")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{id}")
    @GET
    @Operation(summary = "Lista um album", description = "Retorna uma Album do catalogo já cadastrado")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @Path("/teste/{id}")
    @GET
    public Response findByIDOtherApplication(@PathParam("id") Long id){
        return Response.ok(service.getByOtherAPI(id)).build();
    }

}
