package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
    @Operation(summary = "Cria um estilo no catalogo", description = "Cria um estilo no catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response create(StyleRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    @Operation(summary = "Atualiza o estilo do catalogo", description = "Atualiza os estilos do catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response update(StyleResponse response){ return  Response.ok(service.update(response)).build();}

    @GET
    @Operation(summary = "Lista todos os estilos do catalogo", description = "Retorna uma lista de estilos do catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response listAll(){return Response.ok(service.listAll()).build();}

    @Path("/{id}")
    @GET
    @Operation(summary = "Lista um Estilos no catalogo", description = "Lista um Estilo no catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response findById(@PathParam("id") Long id){return Response.ok(service.findById(id)).build();}
}
