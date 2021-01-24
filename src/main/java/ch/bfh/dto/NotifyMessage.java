package ch.bfh.dto;

import lombok.Builder;

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
