package ch.bfh.control;

import ch.bfh.dto.Buchung;
import ch.bfh.dto.NotificationType;
import ch.bfh.dto.NotifyMessage;
import ch.bfh.entity.BuchungEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class WaschplanService {

    @Inject
    private NotifierService notifierService;

    private Map<UUID, BuchungEntity> buchungen = new ConcurrentHashMap<>();

    public List<Buchung> readBuchungen() {
        return buchungen.values().stream().map(Buchung::from).collect(Collectors.toUnmodifiableList());
    }

    public void createBuchung(Buchung buchung) {
        UUID uuid = UUID.randomUUID();
        BuchungEntity buchungEntity = buchung.merge();
        buchungEntity.setId(uuid);
        buchungen.put(uuid, buchungEntity);
        notifierService.broadcast(new NotifyMessage(NotificationType.CREATE_BUCHUNG, buchung));
    }

    public void deleteBuchung(UUID buchungsId) {
        Buchung buchung = Buchung.from(buchungen.get(buchungsId));
        buchungen.remove(buchungsId);
        notifierService.broadcast(new NotifyMessage(NotificationType.DELETE_BUCHUNG, buchung));
    }

    public void updateBuchung(UUID buchungsId, Buchung buchung) {
        buchungen.put(buchungsId, buchung.merge());
        notifierService.broadcast(new NotifyMessage(NotificationType.UPDATE_BUCHUNG, buchung));
    }

}
