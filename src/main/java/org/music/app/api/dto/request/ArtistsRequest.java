package org.music.app.api.dto.request;

public record ArtistsRequest(
        String name,
        String type,
        String dateCreated,
        String origin,
        Long recordCode
) {
}
