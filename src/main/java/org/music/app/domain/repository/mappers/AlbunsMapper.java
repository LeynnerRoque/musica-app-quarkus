package org.music.app.domain.repository.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.api.dto.request.AlbunsRequest;
import org.music.app.api.dto.response.AlbunsResponse;
import org.music.app.domain.model.Albuns;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AlbunsMapper {

    public AlbunsResponse toResponse(Albuns albuns){
        var response = new AlbunsResponse();
        long id = albuns.getId();
        response.setId(id);
        response.setName(albuns.getName());
        response.setArtistsName(albuns.getArtistsByArtistsId().getName());
        response.setStyleName(albuns.getStyleByStyleId().getNameStyle());
        return response;
    }

    public Albuns toEntity(AlbunsRequest request){
        var entity = new Albuns();
        entity.setName(request.name());
        return entity;
    }

    public List<AlbunsResponse> toList(List<Albuns> list){
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse)
                .toList();
    }

}
