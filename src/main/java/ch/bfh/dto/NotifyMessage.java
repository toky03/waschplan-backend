package ch.bfh.dto;

public class NotifyMessage {

    private NotificationType notificationType;
    private Buchung buchung;

    public NotifyMessage(NotificationType notificationType, Buchung buchung) {
        this.notificationType = notificationType;
        this.buchung = buchung;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public Buchung getBuchung() {
        return buchung;
    }
}
