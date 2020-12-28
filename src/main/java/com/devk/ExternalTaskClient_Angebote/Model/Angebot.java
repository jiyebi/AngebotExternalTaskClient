package com.devk.ExternalTaskClient_Angebote.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;

@Builder
@Setter@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Angebot {

    private long id;

    private String name;

    private String adresse;

    private long einzelpreis;

    private String beschreibung;

    private long gesamtpreis;

    private int menge;

}
