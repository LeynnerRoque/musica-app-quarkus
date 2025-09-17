package org.music.app.domain.repository.contracts;

public interface RepositoryTest {

    void shouldBeCreateEntitySuccess();
    void shouldBeCreateEntityFailed();
    void shouldBeUpdateEntitySuccess();
    void shouldBeUpdateEntityFailed();
    void shouldBeListEntitiesSuccess();
    void shouldBeListEntitiesError();
    void shouldBeListOneEntitiesSuccess();
    void shouldBeListOneEntitiesError();


}
