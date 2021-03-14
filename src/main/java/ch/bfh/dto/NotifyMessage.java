package ch.bfh.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class NotifyMessage {

    private NotificationType notificationType;
    private Termin termin;

    public NotifyMessage(NotificationType notificationType, Termin termin) {
        this.notificationType = notificationType;
        this.termin = termin;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Termin getTermin() {
        return termin;
    }
}
