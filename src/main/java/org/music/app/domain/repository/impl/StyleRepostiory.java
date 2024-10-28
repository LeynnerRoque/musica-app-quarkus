package org.music.app.domain.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.music.app.domain.model.Style;

@ApplicationScoped
public class StyleRepostiory implements PanacheRepository<Style> {
}
