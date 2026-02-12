package org.music.app.domain.repository.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.response.ArtistsResponse;
import org.music.app.business.utils.DateConverter;
import org.music.app.domain.model.Artists;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ArtistsMapper {
    private final DateConverter converter;
    public ArtistsMapper(DateConverter converter) {
        this.converter = converter;
    }

    public ArtistsResponse toResponse(Artists artists){
        var response = new ArtistsResponse();
        long id = artists.getId();
        response.setId(id);
        response.setName(artists.getName());
        response.setType(artists.getType());
        response.setOrigin(artists.getOrigin());
        response.setDateCreated(converter.convertToFormat(artists.getDateCreate()));
        response.setRecord(artists.getRecordByRecordId().getName());
        return response;
    }

    public Artists toEntity(ArtistsRequest request){
        var entity = new Artists();
        entity.setName(request.name());
        entity.setType(request.type());
        entity.setOrigin(request.origin());
        entity.setDateCreate(converter.convertToDate(request.dateCreated()));
        return entity;
    }


    public Artists toEntityByResponse(ArtistsResponse request){
        var entity = new Artists();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setType(request.getType());
        entity.setOrigin(request.getOrigin());
        entity.setSpotifyCode(request.getSpotifyCode());
        entity.setDateCreate(converter.convertToDate(request.getDateCreated()));
        return entity;
    }

    public List<ArtistsResponse> tolist(List<Artists> list){
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse)
                .toList();
    }
}
