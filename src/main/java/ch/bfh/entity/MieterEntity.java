package ch.bfh.entity;

import java.util.UUID;

public class MieterEntity {

    private UUID id;
    private String name;

    public MieterEntity(UUID id, String name){
        this.id = id;
        this.name = name;
    }
}
