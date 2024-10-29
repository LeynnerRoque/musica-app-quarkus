package org.music.app.domain.repository.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.api.dto.request.StyleRequest;
import org.music.app.api.dto.response.StyleResponse;
import org.music.app.domain.model.Style;

import java.util.List;
import java.util.Objects;


@ApplicationScoped
public class StyleMapper {
    public StyleResponse toResponse(Style style){
        var response = new StyleResponse();
        long id = style.getId();
        response.setId(id);
        response.setNameStyle(style.getNameStyle());
        return response;
    }


    public Style toEntity(StyleRequest request){
        var entity = new Style();
        entity.setNameStyle(request.nameStyle());
        return entity;
    }


    public Style toEntityByResponse(StyleResponse request){
        var entity = new Style();
        entity.setId(request.getId().intValue());
        entity.setNameStyle(request.getNameStyle());
        return entity;
    }

    public List<StyleResponse> toList(List<Style> list){
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse).toList();
    }

}
