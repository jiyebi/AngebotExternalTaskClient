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

    private long angebotsID;

    private String angebotsName;

    private long angebotsEinzelpreis;

    private long angebotsGesamtpreis;

    private int menge;

}
