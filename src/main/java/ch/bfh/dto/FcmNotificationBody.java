package ch.bfh.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FcmNotificationBody {
    String title;
    String body;
}
