package com.devk.ExternalTaskClient_Angebote.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Setter@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Angebot {

    public Angebot(long angebotsID, String angebotsName, long angebotsEinzelpreis,
                   long angebotsGesamtpreis, LocalDate angebotsfrist, int menge) {
        this.angebotsID = angebotsID;
        this.angebotsName = angebotsName;
        this.angebotsEinzelpreis = angebotsEinzelpreis;
        this.angebotsGesamtpreis = angebotsGesamtpreis;
        this.angebotsfrist = angebotsfrist;
        this.menge = menge;
    }

    public Angebot() {

    }

    private long angebotsID;

    private String angebotsName;

    private long angebotsEinzelpreis;

    private long angebotsGesamtpreis;

    private LocalDate angebotsfrist;

    private int menge;

}
