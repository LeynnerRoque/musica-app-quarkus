package org.music.app.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "albuns")
@Getter
@Setter
public class Albuns {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name = "style_id", referencedColumnName = "id", nullable = false)
    private Style styleByStyleId;
    @ManyToOne
    @JoinColumn(name = "artists_id", referencedColumnName = "id", nullable = false)
    private Artists artistsByArtistsId;

}
