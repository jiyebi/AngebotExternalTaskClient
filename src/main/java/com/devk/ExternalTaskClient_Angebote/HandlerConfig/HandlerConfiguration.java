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
import org.springframework.http.*;
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
                        logger.info("nur ein Test:");

                        final String url = "http://localhost:8085/Angebote/besteAngebote/";


                        ResponseEntity<Angebot[]> responseEntity = restTemplate.getForEntity(url, Angebot[].class);
                        Angebot[] angebot = responseEntity.getBody();
                        MediaType contentType = responseEntity.getHeaders().getContentType();
                        HttpStatus statusCode = responseEntity.getStatusCode();

                        Map<String, Object> variable = new HashMap<String, Object>();
                        variable.put("Angebot", angebot);

                     // TODO: die Liste mit drei Angebote in der Console ausgeben
                        for (Angebot a: angebot) {
                            logger.info(a.toString());
                        }
                        externalTaskService.complete(externalTask, variable);

                    } catch (Exception e) {
                        logger.error("Fehler: ", e);
                        externalTaskService.handleFailure(externalTask, externalTask.getId(), e.getMessage(), 1, 100L);
                    }
                }).open();


        externalTaskClient.subscribe("Startzeit_festlegen").handler((externalTask, externalTaskService) -> {
            externalTaskService.complete(externalTask);

        }).open();

    }
}