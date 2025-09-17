package org.music.app.domain.repository.mappers;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.music.app.base.BaseMock;
import org.music.app.domain.repository.contracts.MappersTest;

@QuarkusTest
public class RecordsMapperTest extends BaseMock  implements MappersTest {

    @Test
    @Override
    public void shoulBeCreateObjectResponseMapper() {
        var mapperObject = recordsMapper.toResponse(entityStubs.getRecords());
        Assertions.assertNotNull(mapperObject);
    }

    @Test
    @Override
    public void shoulBeErrorCreateObjectResponseMapper() {
        Assertions.assertThrows(NullPointerException.class,()->{
            recordsMapper.toResponse(null);
        });
    }
}
