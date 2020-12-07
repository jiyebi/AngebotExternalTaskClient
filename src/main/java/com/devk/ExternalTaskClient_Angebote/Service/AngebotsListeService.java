package com.devk.ExternalTaskClient_Angebote.Service;

import com.devk.ExternalTaskClient_Angebote.HandlerConfig.HandlerConfiguration;
import com.devk.ExternalTaskClient_Angebote.Model.Angebot;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AngebotsListeService {

    private final Logger logger = LoggerFactory.getLogger(HandlerConfiguration.class);
    private final String url = "http://localhost:8085/Angebote/besteAngebote/";

    public AngebotsListeService() {
    }

    public Angebot[] angebotsListeVonAngebotsServerHolen() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Angebot[]> responseEntity = restTemplate.getForEntity(url, Angebot[].class);
        Angebot[] angebotsListe = responseEntity.getBody();
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();

        Map<String, Object> variable = new HashMap<String, Object>();
        variable.put("Angebot", angebotsListe);

        // TODO: die Liste mit drei Angebote in der Console ausgeben
        for (Angebot a : angebotsListe) {
            logger.info(a.toString());
        }
        return angebotsListe;
    }
}
