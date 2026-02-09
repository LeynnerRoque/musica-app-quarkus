package org.music.app.domain.repository.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.api.dto.request.RecordsRequest;
import org.music.app.api.dto.response.RecordsResponse;
import org.music.app.domain.model.Record;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RecordsMapper {

    public RecordsResponse toResponse(Record record){
        var response = new RecordsResponse();
        long id = record.getId();
        response.setId(id);
        response.setName(record.getName());
        return response;
    }

    public Record toEntity(RecordsRequest request){
        var entity = new Record();
        entity.setName(request.name());
        return entity;
    }


    public Record toEntityByResponse(RecordsResponse request){
        var entity = new Record();
        entity.setId(request.getId());
        entity.setName(request.getName());
        return entity;
    }

    public List<RecordsResponse> toList(List<Record> records){
        return records.stream()
                .filter(Objects::nonNull)
                .map(this::toResponse)
                .toList();
    }
}
