package org.music.app.domain.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "artists")
public class Artists {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "type", nullable = true, length = 50)
    private String type;
    @Basic
    @Column(name = "date_create", nullable = true)
    private Date dateCreate;
    @Basic
    @Column(name = "origin", nullable = true, length = 100)
    private String origin;
    @OneToMany(mappedBy = "artistsByArtistsId")
    private Collection<Albuns> albunsById;
    @ManyToOne
    @JoinColumn(name = "record_id", referencedColumnName = "id", nullable = false)
    private Record recordByRecordId;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artists artists = (Artists) o;
        return id == artists.id && Objects.equals(name, artists.name) && Objects.equals(type, artists.type) && Objects.equals(dateCreate, artists.dateCreate) && Objects.equals(origin, artists.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, dateCreate, origin);
    }

    public Collection<Albuns> getAlbunsById() {
        return albunsById;
    }

    public void setAlbunsById(Collection<Albuns> albunsById) {
        this.albunsById = albunsById;
    }

    public Record getRecordByRecordId() {
        return recordByRecordId;
    }

    public void setRecordByRecordId(Record recordByRecordId) {
        this.recordByRecordId = recordByRecordId;
    }
}
