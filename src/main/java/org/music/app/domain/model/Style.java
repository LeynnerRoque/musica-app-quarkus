package org.music.app.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "style")
@Getter
@Setter
public class Style {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name_style", nullable = true, length = 100)
    private String nameStyle;
    @OneToMany(mappedBy = "styleByStyleId")
    private Collection<Albuns> albunsById;
}
