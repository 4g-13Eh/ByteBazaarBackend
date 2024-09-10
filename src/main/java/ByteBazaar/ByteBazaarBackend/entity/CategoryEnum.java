package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public enum CategoryEnum {
    AUDIO(1, "Audio"),
    BUERO(2, "Büro"),
    DROHNEN(3, "Drohnen"),
    FOTO(4, "Foto"),
    VIDEO(5, "Video"),
    GAMING(6, "Gaming"),
    NETZWERK(7, "Netzwerk"),
    NOTEBOOKS(8, "Notebooks"),
    TV(9, "TV"),
    PC(10, "PC"),
    HANDYS(11, "Handys");

    @Id
    @Column(name = "Id")
    private final Integer id;
    private final String displayName;

    CategoryEnum(Integer id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }

    public Integer getId(){
        return id;
    }

    public String getDisplayName(){
        return displayName;
    }
}
