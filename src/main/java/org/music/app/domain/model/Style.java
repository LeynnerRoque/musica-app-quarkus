package org.music.app.domain.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "style")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStyle() {
        return nameStyle;
    }

    public void setNameStyle(String nameStyle) {
        this.nameStyle = nameStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Style style = (Style) o;
        return id == style.id && Objects.equals(nameStyle, style.nameStyle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameStyle);
    }

    public Collection<Albuns> getAlbunsById() {
        return albunsById;
    }

    public void setAlbunsById(Collection<Albuns> albunsById) {
        this.albunsById = albunsById;
    }
}
