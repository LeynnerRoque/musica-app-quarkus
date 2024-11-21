package org.music.app.business.service;

import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.music.app.api.client.ClientAPI;
import org.music.app.api.dto.request.AlbunsRequest;
import org.music.app.api.dto.response.AlbunsResponse;
import org.music.app.domain.repository.impl.AlbunsRepository;
import org.music.app.domain.repository.mappers.AlbunsMapper;
import org.music.app.domain.repository.mappers.ArtistsMapper;
import org.music.app.domain.repository.mappers.StyleMapper;

import java.net.URI;
import java.util.List;
import java.util.Set;

@Slf4j
@ApplicationScoped
public class AlbunsService {

    private final AlbunsRepository repository;
    private final AlbunsMapper mapper;
    private final ArtistsService artistsService;
    private final ArtistsMapper artistsMapper;
    private final StyleService styleService;
    private final StyleMapper styleMapper;


    private final ClientAPI clientAPI;


    public AlbunsService(AlbunsRepository repository, AlbunsMapper mapper,
                         ArtistsService artistsService, ArtistsMapper artistsMapper,
                         StyleService styleService, StyleMapper styleMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.artistsService = artistsService;
        this.artistsMapper = artistsMapper;
        this.styleService = styleService;
        this.styleMapper = styleMapper;
        clientAPI = QuarkusRestClientBuilder
                .newBuilder().baseUri(URI.create("http://localhost:9090/"))
                .build(ClientAPI.class);
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
            e.printStackTrace();
            return "Error in create object";
        }

    }

    @Transactional
    public AlbunsResponse update(AlbunsResponse response){
        var entity = repository.findByIdOptional(response.getId()).get();

        entity.setName(response.getName());

        var artists = artistsService.findByName(response.getArtistsName());
        entity.setArtistsByArtistsId(artistsMapper.toEntityByResponse(artists));

        var style = styleService.findByName(response.getStyleName());
        entity.setStyleByStyleId(styleMapper.toEntityByResponse(style));

        repository.persistAndFlush(entity);
        return mapper.toResponse(entity);

    }

    public AlbunsResponse findById(Long id){
        return mapper.toResponse(repository.findById(id));
    }

    public List<AlbunsResponse> listAll(){
        return mapper.toList(repository.listAll());
    }


    public Set<AlbunsResponse> getByOtherAPI(Long id){
        return clientAPI.getById(id);
    }

}
