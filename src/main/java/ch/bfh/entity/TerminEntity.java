package ch.bfh.entity;

import ch.bfh.dto.Mieter;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Value
public class TerminEntity {

    @Setter
    UUID id;
    LocalDateTime terminBeginn;
    LocalDateTime terminEnde;
    Mieter partei;

}
