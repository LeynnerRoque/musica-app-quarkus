package org.music.app.api.client.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumsSpotifyResponse {

    private String albumType;
    private int totalTracks;
    private String name;
    private String href;
    private ArtistsSpotifyResponse artists;
}
