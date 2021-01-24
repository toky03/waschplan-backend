package ch.bfh.control;

import ch.bfh.dto.Termin;
import ch.bfh.dto.Mieter;
import ch.bfh.dto.NotificationType;
import ch.bfh.dto.NotifyMessage;
import ch.bfh.entity.TerminEntity;
import ch.bfh.entity.MieterEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class WaschplanService {

    @Inject
    private NotifierService notifierService;

    List<MieterEntity> mieterEntities = List.of(
            MieterEntity.builder().id(UUID.randomUUID()).name("Beat & Lisa").build(),
            MieterEntity.builder().id(UUID.randomUUID()).name("Familie Ramseier").build(),
            MieterEntity.builder().id(UUID.randomUUID()).name("Hugo").build(),
            MieterEntity.builder().id(UUID.randomUUID()).name("Frau Brönnimann").build());

    private Map<UUID, TerminEntity> termine = new HashMap<>();
    private Map<UUID, MieterEntity> mieter = mieterEntities.stream().collect(Collectors.toMap(MieterEntity::getId, Function.identity()));

    public List<Termin> readTermine() {
        return termine.values().stream().map(Termin::from).collect(Collectors.toUnmodifiableList());
    }

    public void createTermin(Termin termin) {
        UUID uuid = UUID.randomUUID();
        TerminEntity terminEntity = termin.create(uuid, findMieterById(termin.getParteiId()));
        termine.put(uuid, terminEntity);
        notifierService.broadcast(new NotifyMessage(NotificationType.CREATE_BUCHUNG, termin));
    }

    public void deleteTermin(UUID terminId) {
        Termin termin = Termin.from(termine.get(terminId));
        termine.remove(terminId);
        notifierService.broadcast(new NotifyMessage(NotificationType.DELETE_BUCHUNG, termin));
    }

    public void updateTermin(UUID terminId, Termin termin) {
        termine.put(terminId, termin.merge(findMieterById(termin.getParteiId())));
        notifierService.broadcast(new NotifyMessage(NotificationType.UPDATE_BUCHUNG, termin));
    }

    public List<Mieter> readMieter() {
        return mieter.values().stream().map(Mieter::from).collect(Collectors.toUnmodifiableList());
    }

    private Mieter findMieterById(UUID parteiId){
        MieterEntity partei = mieter.get(parteiId);
        if(partei == null){
            throw new NotFoundException(String.format("Mieter mit Id %s nicht gefunden", parteiId));
        }
        return Mieter.from(partei);
    }

}
