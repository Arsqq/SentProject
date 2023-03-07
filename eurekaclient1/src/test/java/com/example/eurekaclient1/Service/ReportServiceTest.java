package com.example.eurekaclient1.Service;

import com.example.eurekaclient1.Model.Sentiment;
import com.example.eurekaclient1.Model.SentimentReport;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Repo.SentimentReportRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class ReportServiceTest {

    @MockBean
    private SentimentReportRepo sentimentReportRepo;


    @InjectMocks
    private ReportService reportService;

    @Test
    @DisplayName("create")
    void createShouldNotThrowConstraintViolationException() throws IOException {
        SentimentReport sentimentReport = new SentimentReport();
        User user=new User("username","mail","password");
        sentimentReport.setId(1L);
        Date date=new Date();
        sentimentReport.setModerator(user);
        sentimentReport.setPositivePercent(50.5);
        sentimentReport.setFileFormat("txt");
        sentimentReport.setNegativePercent(40.5);
        sentimentReport.setRunAt(date);
        sentimentReport.setAmountOfData(2000.5);
        when(sentimentReportRepo.save(Mockito.any(SentimentReport.class)))
                .thenAnswer(i -> i.getArguments()[0]);

    }



}

