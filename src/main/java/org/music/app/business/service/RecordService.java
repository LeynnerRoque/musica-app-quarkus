package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.music.app.api.dto.request.RecordsRequest;
import org.music.app.api.dto.response.RecordsResponse;
import org.music.app.business.fallbacks.FallbackServiceHandler;
import org.music.app.domain.repository.impl.RecordsRepository;
import org.music.app.domain.repository.mappers.RecordsMapper;

import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
@Slf4j
public class RecordService {

    private final RecordsRepository repository;
    private final RecordsMapper mapper;


    public RecordService(RecordsRepository repository, RecordsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    @Fallback(FallbackServiceHandler.class)
    public String create(RecordsRequest request){
        try{
            repository.persist(mapper.toEntity(request));
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
    public RecordsResponse update(RecordsResponse response){
        try{
            var entity = repository.findByIdOptional(response.getId()).get();
            entity.setName(response.getName());
            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }catch (Exception e){
            log.warn("Error on update Obbject : "+e.getCause());
            return null;
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public RecordsResponse findById(Long id){
        try{
            return mapper.toResponse(repository.findById(id));
        } catch (Exception e) {
            log.warn("Error on lists Obbject : "+e.getCause());
            return null;
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public List<RecordsResponse> listAll(){
        try{
            return mapper.toList(repository.listAll());
        } catch (Exception e) {
            log.warn("Error on lists Obbject : "+e.getCause());
            return null;
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public RecordsResponse findByName(String name){
        try{
            return mapper.toResponse(repository.find("name = :name", Parameters.with("name", name)).firstResult());
        } catch (Exception e) {
            log.warn("Error on find Obbject : "+e.getCause());
            return null;
        }
    }
}
