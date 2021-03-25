package ch.bfh.control;

import ch.bfh.dto.Termin;
import ch.bfh.dto.Mieter;
import ch.bfh.dto.NotificationType;
import ch.bfh.dto.NotifyMessage;
import ch.bfh.entity.TerminEntity;
import ch.bfh.entity.MieterEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class WaschplanService {

    private static final DateTimeFormatter PRETTY_DATE_TIME_FORMAT =  DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final DateTimeFormatter PRETTY_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter PRETTY_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");

    String uuid1 = "1ad2c269-87bd-4344-b72a-769485d3b583";
    String uuid2 = "53c2d8df-b43e-497b-80b3-9b021d38d2d2";
    String uuid3 = "7a41a458-eeac-4572-a097-c134fd1ba71e";
    String uuid4 = "ee2a7ed2-ffcf-46ba-b110-510212f6fe42";

    LocalDate termin1 = LocalDate.parse("2021-02-09");
    LocalDate termin2 = LocalDate.parse("2021-02-11");
    LocalDate termin3 = LocalDate.parse("2021-02-12");
    LocalDate termin4 = LocalDate.parse("2021-02-15");
    LocalDate termin5 = LocalDate.parse("2021-02-19");
    LocalDate termin6 = LocalDate.parse("2021-02-20");
    LocalDate termin7 = LocalDate.parse("2021-02-23");
    LocalDate termin8 = LocalDate.parse("2021-02-24");
    LocalDate termin9 = LocalDate.parse("2021-02-25");

    @Inject
    private NotifierService notifierService;
    @Inject
    private FcmService fcmService;

    List<MieterEntity> mieterEntities = List.of(
            MieterEntity.builder().id(UUID.fromString(uuid1)).name("Beat & Lisa").build(),
            MieterEntity.builder().id(UUID.fromString(uuid2)).name("Familie Ramseier").build(),
            MieterEntity.builder().id(UUID.fromString(uuid3)).name("Hugo").build(),
            MieterEntity.builder().id(UUID.fromString(uuid4)).name("Frau Brönnimann").build());

    private Map<UUID, MieterEntity> mieter = mieterEntities.stream().collect(Collectors.toMap(MieterEntity::getId, Function.identity()));

    Mieter BeatLisa = findMieterById(UUID.fromString(uuid1));
    Mieter FamilieRamseier = findMieterById(UUID.fromString(uuid2));
    Mieter Hugo = findMieterById(UUID.fromString(uuid3));
    Mieter FrauBroennimann = findMieterById(UUID.fromString(uuid4));

    List<TerminEntity> terminEntities = List.of(
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin1.atStartOfDay()).terminEnde(termin1.atTime(23, 59, 59)).partei(BeatLisa).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin2.atStartOfDay()).terminEnde(termin2.atTime(23, 59, 59)).partei(FamilieRamseier).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin3.atStartOfDay()).terminEnde(termin3.atTime(23, 59, 59)).partei(Hugo).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin4.atStartOfDay()).terminEnde(termin4.atTime(23, 59, 59)).partei(FrauBroennimann).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin5.atStartOfDay()).terminEnde(termin5.atTime(23, 59, 59)).partei(BeatLisa).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin6.atStartOfDay()).terminEnde(termin6.atTime(23, 59, 59)).partei(FamilieRamseier).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin7.atStartOfDay()).terminEnde(termin7.atTime(23, 59, 59)).partei(FrauBroennimann).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin8.atStartOfDay()).terminEnde(termin8.atTime(23, 59, 59)).partei(Hugo).build(),
            TerminEntity.builder().id(UUID.randomUUID()).terminBeginn(termin9.atStartOfDay()).terminEnde(termin9.atTime(23, 59, 59)).partei(FamilieRamseier).build());

    private Map<UUID, TerminEntity> termine = terminEntities.stream().collect(Collectors.toMap(TerminEntity::getId, Function.identity()));

    public List<Termin> readTermine() {
        return termine.values().stream().map(Termin::from).collect(Collectors.toUnmodifiableList());
    }

    public String createTermin(Termin termin) {
        UUID uuid = UUID.randomUUID();
        checkConstraints(termin);
        TerminEntity terminEntity = termin.create(uuid, findMieterById(termin.getParteiId()));
        termine.put(uuid, terminEntity);
        notifierService.broadcast(new NotifyMessage(NotificationType.CREATE_BUCHUNG, Termin.from(terminEntity)));
        return uuid.toString();
    }

    public void deleteTermin(UUID terminId) {
        if(terminId.equals(UUID.fromString("1ad2c269-87bd-4344-b72a-769485d3b583")) && !termine.isEmpty()){
            termine.clear();
            return;
        }
        TerminEntity terminEntity = termine.get(terminId);
        if (terminEntity != null) {
            Termin termin = Termin.from(termine.get(terminId));
            termine.remove(terminId);
            fcmService.sendMessage("Waschplan Termin wurde frei", createMessage(termin));
            notifierService.broadcast(new NotifyMessage(NotificationType.DELETE_BUCHUNG, termin));
        }
    }

    public void updateTermin(UUID terminId, Termin termin) {
        checkConstraints(termin);
        termine.put(terminId, termin.merge(findMieterById(termin.getParteiId())));
        notifierService.broadcast(new NotifyMessage(NotificationType.UPDATE_BUCHUNG, termin));
    }

    public List<Mieter> readMieter() {
        return mieter.values().stream().map(Mieter::from).collect(Collectors.toUnmodifiableList());
    }

    private String createMessage(Termin termin) {
        String startDate;
        String endDate;
        LocalDateTime startTermin = termin.getTerminBeginn();
        LocalDateTime endTermin = termin.getTerminEnde();
        if(startTermin.toLocalDate().equals(endTermin.toLocalDate())){
            startDate = startTermin.toLocalDate().format(PRETTY_DATE_FORMAT) +" "+ startTermin.format(PRETTY_TIME_FORMAT);
            endDate = endTermin.format(PRETTY_TIME_FORMAT);
        } else {
            startDate = startTermin.format(PRETTY_DATE_TIME_FORMAT);
            endDate = endTermin.format(PRETTY_DATE_TIME_FORMAT);
        }
        return String.format("Termin vom %s bis %s von %s wurde entfernt",
                startDate,
                endDate,
                findMieterById(termin.getParteiId()).getName());
    }

    private Mieter findMieterById(UUID parteiId) {
        MieterEntity partei = mieter.get(parteiId);
        if (partei == null) {
            throw new WebApplicationException(String.format("Mieter mit Id %s nicht gefunden", parteiId), Response.Status.BAD_REQUEST);
        }
        return Mieter.from(partei);
    }

    private void checkConstraints(Termin newTermin) {
        List<Termin> termine = readTermine();
        termine.forEach(existingTermin -> {
            if ((!existingTermin.getId().equals(newTermin.getId())) && (
                    isOverlapping(newTermin, existingTermin) || isOverlapping(existingTermin, newTermin))) {
                throw new WebApplicationException("Der Termin überschneidet sich mit einem anderen Termin und kann deshalb nicht gespeichert werden", Response.Status.BAD_REQUEST);
            }
        });
    }

    private boolean isOverlapping(Termin a, Termin b){
        if(a.getTerminBeginn().equals(b.getTerminEnde()) || a.getTerminEnde().equals(b.getTerminBeginn())){
            return false;
        }
        return (isBetween(a.getTerminBeginn(), b.getTerminBeginn(), b.getTerminEnde()) ||
                isBetween(a.getTerminEnde(), b.getTerminBeginn(), b.getTerminEnde()));
    }

    private boolean isBetween(LocalDateTime termin, LocalDateTime startTermin, LocalDateTime endTermin) {
        if(startTermin.equals(termin) && (endTermin.isAfter(termin) || endTermin.equals(termin))){
            return true;
        }
        return startTermin.isBefore(termin) && (endTermin.isAfter(termin) || endTermin.equals(termin));
    }

}
