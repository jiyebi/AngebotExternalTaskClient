package com.devk.ExternalTaskClient_Angebote.HandlerConfig;

import java.util.*;

import com.devk.ExternalTaskClient_Angebote.Model.Angebot;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


@Configuration
public class HandlerConfiguration {
    private final Logger logger = LoggerFactory.getLogger(HandlerConfiguration.class);

    @Bean
    public void createTopicSubscriberHandler() {
        ExponentialBackoffStrategy fetchTimer = new ExponentialBackoffStrategy(500L, 2, 500L);
        int maxTasksToFetchWithinOnRequest = 1;

        ExternalTaskClient externalTaskClient = ExternalTaskClient
                .create()
                .baseUrl("http://localhost:8080/engine-rest")
                .maxTasks(3).backoffStrategy(fetchTimer)
                .build();

        logger.info("Angebote werden eingeholt...1111");

        externalTaskClient
                .subscribe("training_angebot_einholen")
                .handler((externalTask, externalTaskService) -> {

                    logger.info("Angebote werden eingeholt...");
                    try {

                        RestTemplate restTemplate = new RestTemplate();
                        Angebot angebotClient = Angebot.builder().build();
                        JSONObject angebotJSONObject = new JSONObject(angebotClient);
                        HttpHeaders headers = new HttpHeaders();
                        HttpEntity request = new HttpEntity(headers);
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                        logger.info("nur ein Test:");

                        final String url = "http://localhost:8085/Angebote/1";

                        Map<String, Object> map = new HashMap<String, Object>();
                        List<Angebot> angebotList = new ArrayList<Angebot>();
                        map.put("AngebotListe", angebotList);
                        angebotList = (List) restTemplate.getForObject(url, Angebot.class);
                        logger.info(angebotList.toString());
                        externalTaskService.complete(externalTask, map);
                        logger.info(angebotList.toString());
                    } catch (Exception e) {
                        logger.error("Fehler: ", e);
                        externalTaskService.handleBpmnError(externalTask, externalTask.getId(), "Something went wrong!" + e);
                    }
                }).open();

        externalTaskClient
                .subscribe("Startzeit_festlegen")
                .handler((externalTask, externalTaskService) -> {
                        externalTaskService.complete(externalTask);
                }).open();
    }
}