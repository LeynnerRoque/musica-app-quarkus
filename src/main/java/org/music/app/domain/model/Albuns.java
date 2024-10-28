package org.music.app.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "albuns")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Albuns albuns = (Albuns) o;
        return id == albuns.id  && Objects.equals(name, albuns.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public Style getStyleByStyleId() {
        return styleByStyleId;
    }

    public void setStyleByStyleId(Style styleByStyleId) {
        this.styleByStyleId = styleByStyleId;
    }

    public Artists getArtistsByArtistsId() {
        return artistsByArtistsId;
    }

    public void setArtistsByArtistsId(Artists artistsByArtistsId) {
        this.artistsByArtistsId = artistsByArtistsId;
    }
}
