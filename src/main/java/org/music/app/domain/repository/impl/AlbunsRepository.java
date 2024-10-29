package org.music.app.domain.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.domain.model.Albuns;

@ApplicationScoped
public class AlbunsRepository implements PanacheRepository<Albuns> {
}
