package com.ByteBazaar.ByteBazaarBackend.entities;

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
