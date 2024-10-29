package org.music.app.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistsResponse {

    private Long id;
    private String name;
    private String type;
    private String dateCreated;
    private String origin;
    private String record;
}
