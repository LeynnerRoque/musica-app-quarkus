package org.music.app.api.client;

import io.quarkus.rest.client.reactive.ClientQueryParam;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.music.app.api.dto.response.AlbunsResponse;

import java.util.List;
import java.util.Set;


@RegisterRestClient(baseUri = "http://localhost:9090")
@Path("/albuns")
@ClientQueryParam(name = "id", value = "")
public interface ClientAPI {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    AlbunsResponse getById(@QueryParam("id") Long id);

}
