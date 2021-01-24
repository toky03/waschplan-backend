package ch.bfh.dto;

import ch.bfh.entity.TerminEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Termin {

    UUID id;
    LocalDateTime terminBeginn;
    LocalDateTime terminEnde;
    UUID parteiId;

    public Termin(){}

    public TerminEntity create(UUID id, Mieter mieter) {
        return TerminEntity
                .builder()
                .id(id)
                .terminBeginn(terminBeginn)
                .terminEnde(terminEnde)
                .partei(mieter)
                .build();
    }

    public TerminEntity merge(Mieter mieter) {
        return TerminEntity
                .builder()
                .id(id)
                .terminBeginn(terminBeginn)
                .terminEnde(terminEnde)
                .partei(mieter)
                .build();
    }



    public static Termin from(TerminEntity terminEntity) {
        return builder()
                .id(terminEntity.getId())
                .terminBeginn(terminEntity.getTerminBeginn())
                .terminEnde(terminEntity.getTerminEnde())
                .parteiId(terminEntity.getPartei().getId()).build();
    }
}
