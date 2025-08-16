package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.music.app.api.dto.request.ArtistsRequest;
import org.music.app.api.dto.response.ArtistsResponse;
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
    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public String create(ArtistsRequest request){
        try{
            var entity = mapper.toEntity(request);
            var record = service.findById(request.recordCode());
            entity.setRecordByRecordId(recordsMapper.toEntityByResponse(record));
            repository.persist(entity);
            return "Created";
        }catch (Exception e){
            log.warn("Error on create Obbject : "+e.getCause());
         return "Error on create object";
        }

    }

    @Transactional
    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public ArtistsResponse update(ArtistsResponse response){
        try{
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
        }catch (Exception e){
            log.warn("Error on update Obbject : "+e.getCause());
            return null;
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public ArtistsResponse findById(Long id){
        try{
            return mapper.toResponse(repository.findById(id));
        } catch (Exception e) {
            log.warn("Error on find Obbject : "+e.getCause());
            return null;
        }

    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public List<ArtistsResponse> listAll(){
        try{
            return mapper.tolist(repository.listAll());
        } catch (Exception e) {
            log.warn("Error on list Obbject : "+e.getCause());
            return null;
        }

    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public ArtistsResponse findByName(String name){
        try{
            return mapper.toResponse(repository.find("name = :name", Parameters.with("name", name)).firstResult());
        } catch (Exception e) {
            return null;
        }

    }
}
