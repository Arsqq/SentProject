package com.example.eurekaclient1.Service;

import com.example.eurekaclient1.Model.CommonWords;
import com.example.eurekaclient1.Model.Sentiment;
import com.example.eurekaclient1.Model.SentimentReport;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Repo.SentimentReportRepo;
import com.example.eurekaclient1.Repo.UserRepository;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SentimentReportRepo sentimentReportRepo;

    @Autowired
    SentimentReport sentimentReport;

    public Double positivePercent(List<Sentiment> list){
        double count=0;

        for(var elem :list){
            if(elem.getSentiment().getSentiment().equals("POSITIVE")){
                count++;
            }
        }

        return count/list.size()*100;
    }


    public Double negativePercent(List<Sentiment> list){
        double count=0;

        for(var elem :list){
            if(elem.getSentiment().getSentiment().equals("NEGATIVE")){
                count++;
            }
        }

        return count/list.size()*100;
    }

    public List<Sentiment> generateSentimentReport() throws JsonProcessingException {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://flaskservice/sentimentList",
                        String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        Date date = new Date();
        List<Sentiment> sentiments=mapper.readValue(response.getBody(), new TypeReference<>() {
        });

        sentimentReport.setFileFormat("txt");
        sentimentReport.setAmountOfData((double) sentiments.size());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user=userRepository.findByUsername(auth.getName());
        sentimentReport.setModerator(user.get());
        sentimentReport.setRunAt(date);
        sentimentReport.setNegativePercent(negativePercent(sentiments));
        sentimentReport.setPositivePercent(positivePercent(sentiments));
        sentimentReportRepo.save(sentimentReport);
        return sentiments;
    }

    public List<CommonWords> generateCommonWords() throws JsonProcessingException {
        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://flaskservice/words",
                        String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        List<CommonWords> commonWords=mapper.readValue(response.getBody(), new TypeReference<>() {
        });
        return commonWords;
    }
}
