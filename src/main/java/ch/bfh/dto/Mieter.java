package ch.bfh.dto;

import ch.bfh.entity.MieterEntity;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Mieter {

    UUID id;
    String name;

    public Mieter(){}

    public static Mieter from(MieterEntity mieterEntity) {
        return builder().id(mieterEntity.getId())
                .name(mieterEntity.getName()).build();
    }
}
