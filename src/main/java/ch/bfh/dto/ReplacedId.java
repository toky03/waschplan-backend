package ch.bfh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ReplacedId {
    String oldId;
    String newId;
}
