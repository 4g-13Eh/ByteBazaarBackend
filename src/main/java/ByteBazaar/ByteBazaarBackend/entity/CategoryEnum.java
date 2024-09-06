package ByteBazaar.ByteBazaarBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
public enum CategoryEnum {
    AUDIO("Audio"),
    BUERO("Büro"),
    DROHNEN("Drohnen"),
    FOTO("Foto"),
    VIDEO("Video"),
    GAMING("Gaming"),
    NETZWERK("Netzwerk"),
    NOTEBOOKS("Notebooks"),
    TV("TV"),
    PC("PC"),
    HANDYS("Handys");

    private final String displayName;

    CategoryEnum(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
