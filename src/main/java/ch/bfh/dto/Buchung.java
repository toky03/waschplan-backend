package ch.bfh.dto;


import ch.bfh.entity.BuchungEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Buchung {

    private UUID id;
    private LocalDateTime buchungBeginn;
    private LocalDateTime buchungEnde;
    private String partei;

    public Buchung() {
    }

    private Buchung(UUID id, LocalDateTime buchungBeginn, LocalDateTime buchungEnde, String partei) {
        this.id = id;
        this.buchungBeginn = buchungBeginn;
        this.buchungEnde = buchungEnde;
        this.partei = partei;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setBuchungBeginn(LocalDateTime buchungBeginn) {
        this.buchungBeginn = buchungBeginn;
    }

    public void setBuchungEnde(LocalDateTime buchungEnde) {
        this.buchungEnde = buchungEnde;
    }

    public void setPartei(String partei) {
        this.partei = partei;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getBuchungBeginn() {
        return buchungBeginn;
    }

    public LocalDateTime getBuchungEnde() {
        return buchungEnde;
    }

    public String getPartei() {
        return partei;
    }

    public BuchungEntity merge() {
        return new BuchungEntity(id, buchungBeginn, buchungEnde, partei);
    }

    public static Buchung from(BuchungEntity buchungEntity) {
        return new Buchung(buchungEntity.getId(), buchungEntity.getBuchungBeginn(), buchungEntity.getBuchungEnde(), buchungEntity.getPartei());
    }
}
