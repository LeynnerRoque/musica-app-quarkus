package org.music.app.domain.repository.mappers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.music.app.base.BaseMock;
import org.music.app.domain.repository.contracts.MappersTest;

@QuarkusTest
public class AlbunsMapperTest extends BaseMock implements MappersTest {

    @Test
    @Override
    public void shoulBeCreateObjectResponseMapper() {
        var mapperObject = albunsMapper.toResponse(entityStubs.getAlbuns());
        Assertions.assertNotNull(mapperObject);
    }

    @Test
    @Override
    public void shoulBeErrorCreateObjectResponseMapper() {
        Assertions.assertThrows(NullPointerException.class,()->{
            albunsMapper.toResponse(null);
                });
    }
}
