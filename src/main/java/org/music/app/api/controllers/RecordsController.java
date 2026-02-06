package org.music.app.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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
    @Operation(summary = "Cria uma Record no catalogo", description = "Cria uma Record no catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response create(RecordsRequest request){
        return Response.ok(service.create(request)).build();
    }

    @PUT
    @Operation(summary = "Atualiza uma Record no catalogo", description = "Atualiza uma Record no catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response update(RecordsResponse response){
        return Response.ok(service.update(response)).build();
    }

    @GET
    @Operation(summary = "Lista todas Records do catalogo", description = "Lista todas Records do catalogo musical")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response listAll(){
        return Response.ok(service.listAll()).build();
    }

    @Path("/{id}")
    @GET
    @Operation(summary = "Busca uma Record no catalogo", description = "Busca uma Record no catalogo musical pelo codigo")
    @APIResponse(responseCode = "200", description = "Sucesso")
    public Response findById(@PathParam("id")Long id){
        return Response.ok(service.findById(id)).build();
    }
}
