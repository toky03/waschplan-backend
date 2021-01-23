package ch.bfh.dto;

import ch.bfh.entity.MieterEntity;
import java.util.UUID;

public class Mieter {

    private UUID id;
    private String name;

    public Mieter() {

    }

    private Mieter(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() { return id; }

    public String getName() { return name;}

    public static Mieter from(MieterEntity mieterEntity) {
        return new Mieter(mieterEntity.getId(), mieterEntity.getName());
    }
}
