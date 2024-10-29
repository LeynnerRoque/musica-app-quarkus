package org.music.app.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.response.ArtistsResponse;
import org.music.app.business.utils.DateConverter;
import org.music.app.domain.repository.impl.ArtistsRepository;
import org.music.app.domain.repository.mappers.ArtistsMapper;
import org.music.app.domain.repository.mappers.RecordsMapper;

import java.util.List;

@ApplicationScoped
public class ArtistsService {

    private  final ArtistsRepository repository;
    private final ArtistsMapper mapper;
    private final RecordService service;
    private final RecordsMapper recordsMapper;
    private final DateConverter converter;

    public ArtistsService(ArtistsRepository repository, ArtistsMapper mapper,
                          RecordService service, RecordsMapper recordsMapper,
                          DateConverter converter) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
        this.recordsMapper = recordsMapper;
        this.converter = converter;
    }

    @Transactional
    public String create(ArtistsRequest request){
        var entity = mapper.toEntity(request);
        var record = service.findById(request.recordCode());
        entity.setRecordByRecordId(recordsMapper.toEntityByResponse(record));
        repository.persist(entity);
        return "Created";
    }

    @Transactional
    public ArtistsResponse update(ArtistsResponse response){
        var entity = repository.findByIdOptional(response.getId()).get();
        var record = service.findByName(response.getRecord());
        entity.setId(response.getId().intValue());
        entity.setName(response.getName());
        entity.setType(response.getType());
        entity.setOrigin(response.getOrigin());
        entity.setDateCreate(converter.convertToDate(response.getDateCreated()));
        entity.setRecordByRecordId(recordsMapper.toEntityByResponse(record));

        repository.persistAndFlush(entity);
        return response;
    }

    public ArtistsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id));
    }

    public List<ArtistsResponse> listAll(){
        return mapper.tolist(repository.listAll());
    }
}
