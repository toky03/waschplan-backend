package ch.bfh.dto;

import ch.bfh.entity.TerminEntity;
import ch.bfh.util.LocalDateTimeDeserializer;
import lombok.*;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
public class Termin {

    String id;
    @JsonbTypeDeserializer(LocalDateTimeDeserializer.class)
    LocalDateTime terminBeginn;
    @JsonbTypeDeserializer(LocalDateTimeDeserializer.class)
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
                .id(UUID.fromString(id))
                .terminBeginn(terminBeginn)
                .terminEnde(terminEnde)
                .partei(mieter)
                .build();
    }



    public static Termin from(TerminEntity terminEntity) {
        return builder()
                .id(terminEntity.getId().toString())
                .terminBeginn(terminEntity.getTerminBeginn())
                .terminEnde(terminEntity.getTerminEnde())
                .parteiId(terminEntity.getPartei().getId()).build();
    }

    public String toJson(){
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(this);
    }
}
