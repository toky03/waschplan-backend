package ch.bfh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@RegisterForReflection
public class FcmResponse {

    public FcmResponse(){}

    @JsonProperty("multicast_id")
    @JsonbProperty("multicast_id")
    String multicastId;
    Integer success;
    Integer failure;
    @JsonProperty("canonical_ids")
    @JsonbProperty("canonical_ids")
    Integer canonicalIds;
    List<FcmResult> results;

}
