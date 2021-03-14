package ch.bfh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@Data
@AllArgsConstructor
@RegisterForReflection
public class FcmResult {

    public FcmResult() {
    }

    @JsonProperty("message_id")
    @JsonbProperty("message_id")
    String messageId;
    String error;

}
