package org.music.app.api.client.response;

import lombok.Getter;
import lombok.Setter;
import org.music.app.api.client.response.composes.ImageSpotifyResponse;

import java.util.List;

@Getter
@Setter
public class ArtistsSpotifyResponse {
    private String href;
    private String id;
    private String name;
    private String type;
    private List<String> genres;
    private List<ImageSpotifyResponse> images;
    private Integer popularity;
    private String uri;

}
