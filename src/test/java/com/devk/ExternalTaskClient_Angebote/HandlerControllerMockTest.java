package com.devk.ExternalTaskClient_Angebote;

import com.devk.ExternalTaskClient_Angebote.Controller.HandlerController;
import com.devk.ExternalTaskClient_Angebote.Model.Angebot;
import com.devk.ExternalTaskClient_Angebote.Service.HandlerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Testing ExternalClient Controller...")
public class HandlerControllerMockTest {

    private HandlerService mockHandlerService;
    private HandlerController handlerController;
    private MockMvc mvc;

    @BeforeEach
    void setup(){
        mockHandlerService = Mockito.mock(HandlerService.class);
        handlerController = new HandlerController(mockHandlerService);
        this.mvc = MockMvcBuilders.standaloneSetup(handlerController).build();
    }

    @Test
    @DisplayName("GetThreeBestOffers")
    void getThreeBestOffersFromServer() throws Exception {

        List<Angebot> bestOffers = new ArrayList<>();
        Angebot testAngebot1 = Angebot.builder()
                .adresse("Bolivia (Plurinational State of)")
                .beschreibung("Art Sellers")
                .einzelpreis(598)
                .gesamtpreis(171)
                .id(9L)
                .name("Max Little")
                .build();
        Angebot testAngebot2 = Angebot.builder()
                .adresse("Czechia")
                .beschreibung("Olive Green")
                .einzelpreis(524)
                .gesamtpreis(718)
                .id(5L)
                .name("Oscar Ruitt")
                .build();
        Angebot testAngebot3 = Angebot.builder()
                .adresse("Saint Lucia")
                .beschreibung("Mel Practiss")
                .einzelpreis(8208467)
                .gesamtpreis(849)
                .id(2L)
                .name("Kay Mart")
                .build();
        bestOffers.add(testAngebot1);
        bestOffers.add(testAngebot2);
        bestOffers.add(testAngebot3);
        
     Mockito.when(mockHandlerService.angebotsListeVonAngebotsServerHolen()).thenReturn(bestOffers.toString());

       //when
        String actualResult = handlerController.getThreeBestOffersFromServer("abc",3);

        //then
        assertThat(actualResult, is(bestOffers.toString()));

                mvc.perform(get("/GetThreeBestAngebote/bestOffers?plz=abc&anzahl=3")
                                        .contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(status().isOk())
                                       .andExpect(content().string(bestOffers.toString()));
    }

}
