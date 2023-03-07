package com.example.eurekaclient1.Controllers;

import com.example.eurekaclient1.Model.EntityPart;
import com.example.eurekaclient1.Model.Sentiment;
import com.example.eurekaclient1.Model.SentimentPart;
import com.example.eurekaclient1.Service.ReportService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class SentimentControllerTest {



    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private ReportService reportService;

    @InjectMocks
    private SentimentController sentimentController;



    @Test
    @WithMockUser(username = "Ars",password = "123456",roles = "ADMIN")
    @DisplayName("getAllSentiments")
    void getAllSentiments_RoleAdmin() throws Exception {
        Sentiment sentiment=new Sentiment();
        EntityPart entityPart=new EntityPart();
        SentimentPart sentimentPart=new SentimentPart();
        entityPart.setName("MARK");
        entityPart.setType("PERSON");
        sentimentPart.setSentiment("Positive");
        sentimentPart.setTextContent("Oh hi Mark");
        sentimentPart.setPolarity(99.9);
        List<EntityPart> entities=List.of(entityPart);
        sentiment.setEntities(entities);
        sentiment.setSentiment(sentimentPart);
        List<Sentiment> sentiments = List.of(sentiment);
        when(reportService.generateSentimentReport()).thenReturn(sentiments);
        mockMvc.perform(
                        get("/api/sentiment/getSentimentList")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].entities[0].name").value("MARK"));
    }
}
