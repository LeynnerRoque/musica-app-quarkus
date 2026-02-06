package org.music.app.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.music.app.api.client.ClientAPI;
import org.music.app.api.dto.request.AlbunsRequest;
import org.music.app.api.dto.response.AlbunsResponse;
import org.music.app.business.fallbacks.FallbackServiceHandler;
import org.music.app.domain.repository.impl.AlbunsRepository;
import org.music.app.domain.repository.mappers.AlbunsMapper;
import org.music.app.domain.repository.mappers.ArtistsMapper;
import org.music.app.domain.repository.mappers.StyleMapper;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@ApplicationScoped
public class AlbunsService {

    private final AlbunsRepository repository;
    private final AlbunsMapper mapper;
    private final ArtistsService artistsService;
    private final ArtistsMapper artistsMapper;
    private final StyleService styleService;
    private final StyleMapper styleMapper;

    @RestClient
    private ClientAPI clientAPI;


    public AlbunsService(AlbunsRepository repository, AlbunsMapper mapper,
                         ArtistsService artistsService, ArtistsMapper artistsMapper,
                         StyleService styleService, StyleMapper styleMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.artistsService = artistsService;
        this.artistsMapper = artistsMapper;
        this.styleService = styleService;
        this.styleMapper = styleMapper;
    }


    @Transactional
    public String create(AlbunsRequest request){
        try{
            var entity = mapper.toEntity(request);

            var artists = artistsService.findByName(request.artistsName());
            entity.setArtistsByArtistsId(artistsMapper.toEntityByResponse(artists));

            var style = styleService.findByName(request.styleName());
            entity.setStyleByStyleId(styleMapper.toEntityByResponse(style));

            repository.persist(entity);

            return "Created";

        }catch (Exception e){
            log.warn("Error on create Object : "+e.getCause());
            return "Error in create object";
        }

    }

    @Transactional
    public AlbunsResponse update(AlbunsResponse response){
        try{
            var entity = repository.findByIdOptional(response.getId()).get();

            entity.setName(response.getName());

            var artists = artistsService.findByName(response.getArtistsName());
            entity.setArtistsByArtistsId(artistsMapper.toEntityByResponse(artists));

            var style = styleService.findByName(response.getStyleName());
            entity.setStyleByStyleId(styleMapper.toEntityByResponse(style));

            repository.persistAndFlush(entity);
            return mapper.toResponse(entity);
        } catch (Exception e) {
            log.warn("Error on update Object : "+e.getCause());
            return null;
        }
    }

    public AlbunsResponse findById(Long id){
        try{
            return mapper.toResponse(repository.findById(id));
        } catch (Exception e) {
            log.warn("Error in find Object : "+e.getCause());
            return null;
        }

    }

    public List<AlbunsResponse> listAll(){
        try{
            return mapper.toList(repository.listAll());
        } catch (Exception e) {
            log.warn("Error on lists Object : "+e.getCause());
            return null;
        }

    }

    public AlbunsResponse getByOtherAPI(Long id){
        try{
            return clientAPI.getById(id);
        } catch (Exception e) {
            log.warn("Error on find Object : "+e.getCause());
            return null;
        }
    }


}
