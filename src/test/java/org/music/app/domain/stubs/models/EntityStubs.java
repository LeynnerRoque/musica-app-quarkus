package org.music.app.domain.stubs.models;

import org.music.app.domain.model.Albuns;
import org.music.app.domain.model.Artists;
import org.music.app.domain.model.Record;
import org.music.app.domain.model.Style;

import java.sql.Date;

public class EntityStubs {

    private Albuns albuns;
    private Style style;
    private Artists artists;
    private Record records;

    public Albuns getAlbuns(){
        albuns = new Albuns();
        albuns.setId(1);
        albuns.setName("Test Stubs");
        albuns.setArtistsByArtistsId(getArtists());
        albuns.setStyleByStyleId(getStyle());
        return albuns;
    }

    public Albuns getAlbunsNullable(){
        albuns = new Albuns();
        return albuns;
    }

    public Style getStyle() {
        style = new Style();
        style.setId(1);
        style.setNameStyle("Stub Style");
        return style;
    }

    public Artists getArtists(){
        artists = new Artists();
        artists.setId(1);
        artists.setName("The Stubs");
        artists.setType("Stubies");
        artists.setOrigin("England");
        artists.setDateCreate(new Date(2025,10,10));
        artists.setRecordByRecordId(getRecords());
        return artists;
    }


    public Record getRecords() {
        records = new Record();
        records.setId(1);
        records.setName("The Stub Records");
        return records;
    }
}
