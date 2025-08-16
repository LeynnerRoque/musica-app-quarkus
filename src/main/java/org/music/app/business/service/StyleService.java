package org.music.app.business.service;

import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.music.app.api.dto.request.StyleRequest;
import org.music.app.api.dto.response.StyleResponse;
import org.music.app.domain.repository.impl.StyleRepostiory;
import org.music.app.domain.repository.mappers.StyleMapper;

import java.time.temporal.ChronoUnit;
import java.util.List;

@ApplicationScoped
@Slf4j
public class StyleService {

    private final StyleRepostiory repostiory;

    private final StyleMapper mapper;

    public StyleService(StyleRepostiory repostiory, StyleMapper mapper) {
        this.repostiory = repostiory;
        this.mapper = mapper;
    }

    @Transactional
    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public String create(StyleRequest request){
        try{
            repostiory.persist(mapper.toEntity(request));
            return "Created";
        }catch (Exception e){
            log.warn("Error on create Obbject : "+e.getCause());
            return "Error on create object";
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public StyleResponse findById(Long id){
        try{
            var response = repostiory.findById(id);
            return mapper.toResponse(response);
        } catch (Exception e) {
            log.warn("Error on create Obbject : "+e.getCause());
            return null;
        }
    }

    @Transactional
    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public StyleResponse update(StyleResponse styleResponse){
        try{
            var entity = repostiory.findByIdOptional(styleResponse.getId()).get();
            entity.setNameStyle(styleResponse.getNameStyle());
            repostiory.persistAndFlush(entity);
            return mapper.toResponse(entity);
        }catch (Exception e){
            log.warn("Error on update Obbject : "+e.getCause());
            return null;
        }

    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public List<StyleResponse> listAll(){
        try{
            return mapper.toList(repostiory.listAll());
        } catch (Exception e) {
            log.warn("Error on lists Obbject : "+e.getCause());
            return null;
        }
    }

    @Retry(maxRetries = 5, delay = 200, delayUnit = ChronoUnit.MILLIS)
    @Timeout(1000)
    @CircuitBreaker(requestVolumeThreshold = 4,failureRatio = 0.75, delay = 10, delayUnit = ChronoUnit.SECONDS)
    public StyleResponse findByName(String name){
        try{
            return mapper.toResponse(repostiory.find("nameStyle = :name", Parameters.with("name", name)).firstResult());
        } catch (Exception e) {
            log.warn("Error on find Obbject : "+e.getCause());
            return null;
        }
    }
}
