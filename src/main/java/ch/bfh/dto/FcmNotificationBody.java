package ch.bfh.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@RegisterForReflection
public class FcmNotificationBody {
    String title;
    String body;
}
