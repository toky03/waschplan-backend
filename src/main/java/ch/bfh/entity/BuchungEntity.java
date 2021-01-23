package ch.bfh.entity;

import ch.bfh.dto.Mieter;

import java.time.LocalDateTime;
import java.util.UUID;

public class BuchungEntity {

    private UUID id;
    private LocalDateTime buchungBeginn;
    private LocalDateTime buchungEnde;
    private Mieter partei;

    public BuchungEntity(UUID id, LocalDateTime buchungBeginn, LocalDateTime buchungEnde, Mieter partei) {
        this.id = id;
        this.buchungBeginn = buchungBeginn;
        this.buchungEnde = buchungEnde;
        this.partei = partei;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getBuchungBeginn() {
        return buchungBeginn;
    }

    public void setBuchungBeginn(LocalDateTime buchungBeginn) {
        this.buchungBeginn = buchungBeginn;
    }

    public LocalDateTime getBuchungEnde() {
        return buchungEnde;
    }

    public void setBuchungEnde(LocalDateTime buchungEnde) {
        this.buchungEnde = buchungEnde;
    }

    public Mieter getPartei() {
        return partei;
    }

    public void setPartei(Mieter partei) {
        this.partei = partei;
    }
}
