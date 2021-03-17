package ch.bfh.dto;


import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@Builder
@Data
@AllArgsConstructor
@RegisterForReflection
public class ReplacedId {
    String oldId;
    String newId;

    public String toJson(){
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(this);
    }
}
