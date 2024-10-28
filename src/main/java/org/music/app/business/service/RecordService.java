package org.music.app.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.music.app.api.dto.request.RecordsRequest;
import org.music.app.api.dto.response.RecordsResponse;
import org.music.app.domain.repository.impl.RecordsRepository;
import org.music.app.domain.repository.mappers.RecordsMapper;

import java.util.List;

@ApplicationScoped
public class RecordService {

    private final RecordsRepository repository;
    private final RecordsMapper mapper;


    public RecordService(RecordsRepository repository, RecordsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public String create(RecordsRequest request){
        repository.persist(mapper.toEntity(request));
        return "Created";
    }

    public RecordsResponse update(RecordsResponse response){
        var entity = repository.findByIdOptional(response.getId()).get();
        entity.setName(response.getName());
        repository.persistAndFlush(entity);
        return mapper.toResponse(entity);
    }

    public RecordsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id));
    }

    public List<RecordsResponse> listAll(){
        return mapper.toList(repository.listAll());
    }
}
