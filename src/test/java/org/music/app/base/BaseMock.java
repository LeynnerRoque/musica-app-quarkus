package org.music.app.base;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.music.app.domain.repository.impl.AlbunsRepository;
import org.music.app.domain.repository.mappers.AlbunsMapper;
import org.music.app.domain.repository.mappers.ArtistsMapper;
import org.music.app.domain.repository.mappers.RecordsMapper;
import org.music.app.domain.repository.mappers.StyleMapper;
import org.music.app.domain.stubs.models.EntityStubs;
import org.music.app.domain.stubs.requests.RequestStubs;

@QuarkusTest
public class BaseMock {

    public EntityStubs entityStubs;
    public RequestStubs requestStubs;

    @Inject
    public AlbunsMapper albunsMapper;

    @Inject
    public StyleMapper styleMapper;

    @Inject
    public RecordsMapper recordsMapper;

    @Inject
    public ArtistsMapper artistsMapper;


    @InjectMock
    public AlbunsRepository albunsRepository;


    @BeforeEach
    void setup(){
        entityStubs = new EntityStubs();
        requestStubs = new RequestStubs();
    }


}
