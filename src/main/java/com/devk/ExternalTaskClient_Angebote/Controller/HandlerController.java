package com.devk.ExternalTaskClient_Angebote.Controller;

import com.devk.ExternalTaskClient_Angebote.Service.HandlerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/GetThreeBestAngebote")
public class HandlerController {

private final HandlerService handlerService;

    public HandlerController(HandlerService handlerService) {
        this.handlerService = handlerService;
    }

    @RequestMapping(
            value = "/bestOffers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getThreeBestOffersFromServer(@RequestParam String plz, Integer anzahl) throws JsonProcessingException
    {return handlerService.angebotsListeVonAngebotsServerHolen();}
}
