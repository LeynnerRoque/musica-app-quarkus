package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
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
        try{
            repository.persist(mapper.toEntity(request));
            return "Created";
        }catch (Exception e){
            return "Error on create object";
        }
    }

    @Transactional
    public RecordsResponse update(RecordsResponse response){
        try{
            var entity = repository.findByIdOptional(response.getId()).get();
            entity.setName(response.getName());
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }catch (Exception e){
            return null;
        }
    }

    public RecordsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id));
    }

    public List<RecordsResponse> listAll(){
        return mapper.toList(repository.listAll());
    }

    public RecordsResponse findByName(String name){
        return mapper.toResponse(repository.find("name = :name", Parameters.with("name", name)).firstResult());
    }
}
