package org.music.app.api.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.music.app.api.dto.response.AlbunsResponse;

import java.util.Set;

@Path("albuns/")
@RegisterRestClient(configKey = "albuns-getBy")
public interface ClientAPI {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    Set<AlbunsResponse> getById(@QueryParam("id") Long id);

}
