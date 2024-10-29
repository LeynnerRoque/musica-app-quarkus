package org.music.app.api.dto.request;

public record AlbunsRequest (
        String name,
        String artistsName,
        String styleName
){
}
