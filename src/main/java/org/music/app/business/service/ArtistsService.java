package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.response.ArtistsResponse;
import org.music.app.business.fallbacks.FallbackServiceHandler;
import org.music.app.business.utils.DateConverter;
import org.music.app.domain.repository.impl.ArtistsRepository;
import org.music.app.domain.repository.mappers.ArtistsMapper;
import org.music.app.domain.repository.mappers.RecordsMapper;

import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
@Slf4j
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
        try{
            var entity = mapper.toEntity(request);
            var record = service.findById(request.recordCode());
            entity.setRecordByRecordId(recordsMapper.toEntityByResponse(record));
            repository.persist(entity);
            return "Created";
        }catch (Exception e){
            log.warn("Error on create Object : "+e.getCause());
         return "Error on create object";
        }

    }

    @Transactional
    public ArtistsResponse update(ArtistsResponse response){
        try{
            var entity = repository.findByIdOptional(response.getId()).get();
            var record = service.findByName(response.getRecord());
            entity.setId(response.getId());
            entity.setName(response.getName());
            entity.setType(response.getType());
            entity.setOrigin(response.getOrigin());
            entity.setDateCreate(converter.convertToDate(response.getDateCreated()));
            entity.setRecordByRecordId(recordsMapper.toEntityByResponse(record));

            repository.persistAndFlush(entity);
            return response;
        }catch (Exception e){
            log.warn("Error on update Object : "+e.getCause());
            return null;
        }
    }

    public ArtistsResponse findById(Long id){
        try{
            return listAll().stream()
                    .filter(a->a.getId().equals(id))
                    .findFirst().get();
            //return mapper.toResponse(repository.find("id = :id", Parameters.with("id", id)).firstResult());
        } catch (Exception e) {
            log.warn("Error on find Object : "+e.getCause());
            return null;
        }

    }

    public List<ArtistsResponse> listAll(){
        try{
            return mapper.tolist(repository.listAll());
        } catch (Exception e) {
            log.warn("Error on list Object : "+e.getCause());
            return null;
        }

    }

    public ArtistsResponse findByName(String name){
        try{
            return mapper.toResponse(repository.find("name = :name", Parameters.with("name", name)).firstResult());
        } catch (Exception e) {
            return null;
        }

    }
}
