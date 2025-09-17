package org.music.app.domain.stubs.requests;

import org.music.app.api.dto.request.AlbunsRequest;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.request.RecordsRequest;
import org.music.app.api.dto.request.StyleRequest;

public class RequestStubs {

    public AlbunsRequest getAlbunsRequest() {
        return new AlbunsRequest("Stub Albuns Request","Request Names","Stub Styles");
    }

    public StyleRequest getStylesRequests(){
        return new StyleRequest("Stub Style");
    }

    public RecordsRequest getRecordsRequests(){
        return new RecordsRequest("Records Stubs");
    }

    public ArtistsRequest getArtistsRequests(){
        return new ArtistsRequest("Artists Requests","Stub Tests","10/10/2025","England",1L);
    }
}
