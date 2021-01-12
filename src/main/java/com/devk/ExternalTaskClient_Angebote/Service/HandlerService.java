package com.devk.ExternalTaskClient_Angebote.Service;

import com.devk.ExternalTaskClient_Angebote.HandlerConfig.HandlerConfiguration;
<<<<<<< HEAD:src/main/java/com/devk/ExternalTaskClient_Angebote/Service/AngebotsListeService.java
=======
import com.fasterxml.jackson.core.JsonProcessingException;
>>>>>>> b3993bfaa8d4ad26e5faff9ac5ab90d3de08e0a6:src/main/java/com/devk/ExternalTaskClient_Angebote/Service/HandlerService.java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class HandlerService {

    private final Logger logger = LoggerFactory.getLogger(HandlerConfiguration.class);

    public HandlerService() {
    }

    public String angebotsListeVonAngebotsServerHolen() throws JsonProcessingException {

        String plz = "abc";
        Integer anzahl = 3;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "http://localhost:8085/Angebote/besteAngebote?plz=abc&anzahl=3";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(plz)
                .queryParam(anzahl.toString());

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonResponseBody = response.getBody();


        Map<String, Object> variable = new HashMap<String, Object>();
        variable.put("AngebotsListe", jsonResponseBody);

        return  jsonResponseBody;
    }
}
