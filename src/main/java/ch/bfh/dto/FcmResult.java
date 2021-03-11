package ch.bfh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FcmResult {

    @JsonbProperty("message_id")
    String messageId;
    String error;

}
