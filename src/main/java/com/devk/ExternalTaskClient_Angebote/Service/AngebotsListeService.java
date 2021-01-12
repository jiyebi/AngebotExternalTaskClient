package com.devk.ExternalTaskClient_Angebote.Service;

import com.devk.ExternalTaskClient_Angebote.HandlerConfig.HandlerConfiguration;
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
public class AngebotsListeService {

    private final Logger logger = LoggerFactory.getLogger(HandlerConfiguration.class);
    private final String url = "http://localhost:8085/Angebote/besteAngebote";
    private Object plz;
    private Object anzahl;

    public AngebotsListeService() {
    }

    public String angebotsListeVonAngebotsServerHolen() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("plz", "abc")
                .queryParam("anzahl", "3");

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonResponseBody = response.getBody();


        Map<String, Object> variable = new HashMap<String, Object>();
        variable.put("AngebotsListe", jsonResponseBody);

        return jsonResponseBody;
    }
}
