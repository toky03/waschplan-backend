package ch.bfh.dto;


import lombok.Builder;
import lombok.Value;

import javax.json.bind.annotation.JsonbProperty;
import java.util.Set;

@Value
@Builder
public class FcmMessage {

    @JsonbProperty("registration_ids")
    Set<String> registrationIds;
    FcmNotificationBody notification;
}
