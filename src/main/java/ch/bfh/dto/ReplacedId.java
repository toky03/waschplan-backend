package ch.bfh.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Builder
@Data
@AllArgsConstructor
public class ReplacedId {
    String oldId;
    String newId;

    public String toJson(){
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(this);
    }
}
