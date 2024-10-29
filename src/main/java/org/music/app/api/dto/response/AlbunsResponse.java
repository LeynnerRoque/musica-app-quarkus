package org.music.app.api.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbunsResponse {

    private Long id;
    private String name;
    private String artistsName;
    private String styleName;

}
