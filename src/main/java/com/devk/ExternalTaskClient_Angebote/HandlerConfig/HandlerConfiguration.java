package com.devk.ExternalTaskClient_Angebote.HandlerConfig;

<<<<<<< HEAD
import com.devk.ExternalTaskClient_Angebote.Service.AngebotsListeService;
=======
import java.util.*;

import com.devk.ExternalTaskClient_Angebote.Controller.HandlerController;
import com.devk.ExternalTaskClient_Angebote.Service.HandlerService;
>>>>>>> b3993bfaa8d4ad26e5faff9ac5ab90d3de08e0a6
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD

import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> b3993bfaa8d4ad26e5faff9ac5ab90d3de08e0a6


@Configuration
public class HandlerConfiguration {
    private final Logger logger = LoggerFactory.getLogger(HandlerConfiguration.class);

    private final HandlerController handlerController;

    public HandlerConfiguration(HandlerService service, HandlerController handlerController) {
        this.handlerController = handlerController;
    }

    @Bean
    public void createTopicSubscriberHandler() {
        ExponentialBackoffStrategy fetchTimer = new ExponentialBackoffStrategy(500L, 2, 500L);
        int maxTasksToFetchWithinOnRequest = 1;

        ExternalTaskClient externalTaskClient = ExternalTaskClient
                .create()
                .baseUrl("http://localhost:8080/engine-rest")
                .maxTasks(3).backoffStrategy(fetchTimer)
                .build();

        externalTaskClient
                .subscribe("training_angebot_einholen")
                .handler((externalTask, externalTaskService) -> {

                    logger.info("Angebote werden eingeholt...");
                    try {

                        Map<String, Object> variable = new HashMap<String, Object>();
<<<<<<< HEAD
                        variable.put("AngebotsListe", service.angebotsListeVonAngebotsServerHolen()); // Angebotsliste wird vom Server geholt
                        logger.info(service.angebotsListeVonAngebotsServerHolen());
=======
                        variable.put("threeBestOffers", handlerController.getThreeBestOffersFromServer("abc",3));
                        logger.info(handlerController.getThreeBestOffersFromServer("abc",3).toString());
>>>>>>> b3993bfaa8d4ad26e5faff9ac5ab90d3de08e0a6
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