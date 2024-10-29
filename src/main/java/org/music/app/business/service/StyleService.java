package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.music.app.api.dto.request.StyleRequest;
import org.music.app.api.dto.response.StyleResponse;
import org.music.app.domain.repository.impl.StyleRepostiory;
import org.music.app.domain.repository.mappers.StyleMapper;

import java.util.List;

@ApplicationScoped
public class StyleService {

    private final StyleRepostiory repostiory;

    private final StyleMapper mapper;

    public StyleService(StyleRepostiory repostiory, StyleMapper mapper) {
        this.repostiory = repostiory;
        this.mapper = mapper;
    }

    @Transactional
    public String create(StyleRequest request){
        repostiory.persist(mapper.toEntity(request));
        return "Created";
    }

    public StyleResponse findById(Long id){
        var response = repostiory.findById(id);
        return mapper.toResponse(response);
    }

    public List<StyleResponse> listAll(){
        return mapper.toList(repostiory.listAll());
    }

    @Transactional
    public StyleResponse update(StyleResponse styleResponse){
        var entity = repostiory.findByIdOptional(styleResponse.getId()).get();
        entity.setNameStyle(styleResponse.getNameStyle());
        repostiory.persistAndFlush(entity);
        return mapper.toResponse(entity);
    }

    public StyleResponse findByName(String name){
        return mapper.toResponse(repostiory.find("nameStyle = :name", Parameters.with("name", name)).firstResult());
    }
}
