package ch.bfh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FcmResponse {

    @JsonbProperty("multicast_id")
    String multicastId;
    Integer success;
    Integer failure;
    @JsonbProperty("canonical_ids")
    Integer canonicalIds;
    List<FcmResult> results;

}
