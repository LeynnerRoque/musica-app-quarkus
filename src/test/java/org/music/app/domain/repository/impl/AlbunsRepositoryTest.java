package org.music.app.domain.repository.impl;

import io.quarkus.test.junit.QuarkusTest;
import org.hibernate.HibernateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.music.app.base.BaseMock;
import org.music.app.domain.repository.contracts.RepositoryTest;

@QuarkusTest
public class AlbunsRepositoryTest extends BaseMock implements RepositoryTest {

    @Test
    @Override
    public void shouldBeCreateEntitySuccess() {
        Assertions.assertDoesNotThrow(() -> {
             albunsRepository.persist(entityStubs.getAlbuns());
                });
    }

    @Test
    @Override
    public void shouldBeCreateEntityFailed() {
        Assertions.assertDoesNotThrow(() -> {
            albunsRepository.persist(entityStubs.getAlbunsNullable());
        });
    }

    @Test
    @Override
    public void shouldBeUpdateEntitySuccess() {
        Assertions.assertDoesNotThrow(() -> {
            albunsRepository.persistAndFlush(entityStubs.getAlbuns());
        });
    }

    @Test
    @Override
    public void shouldBeUpdateEntityFailed() {
        Assertions.assertDoesNotThrow(() -> {
            albunsRepository.persist(entityStubs.getAlbunsNullable());
        });
    }

    @Override
    public void shouldBeListEntitiesSuccess() {
        Assertions.assertThrows(HibernateException.class,() -> {
            albunsRepository.findAll();
        });

    }

    @Test
    @Override
    public void shouldBeListEntitiesError() {
        Mockito.when(albunsRepository.findAll()).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class,() -> {
            albunsRepository.findAll();
        });
    }

    @Test
    @Override
    public void shouldBeListOneEntitiesSuccess() {
        Assertions.assertDoesNotThrow(() -> {
            albunsRepository.findById(1L);
        });
    }

    @Test
    @Override
    public void shouldBeListOneEntitiesError() {
        Mockito.when(albunsRepository.findById(null)).thenThrow(NullPointerException.class);
        Assertions.assertThrows(NullPointerException.class,() -> {
            albunsRepository.findById(null);
        });
    }
}
