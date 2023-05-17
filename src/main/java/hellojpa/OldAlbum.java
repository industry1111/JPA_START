package hellojpa;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("A") //DB에 들어가는 DTPYE 값 설정 (DEFAULT Entity Name )
public class OldAlbum extends OldItem{
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
