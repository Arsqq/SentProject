package com.example.eurekaclient1.Controllers;
import com.example.eurekaclient1.Model.CommonWords;
import com.example.eurekaclient1.Model.Sentiment;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/sentiment")
@RestController
public class SentimentController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportService reportService;

    @PostMapping("/flaskCall")
    public String sentimentServiceCall(@RequestBody String query)  {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> s = new org.springframework.http.HttpEntity<>(query, headers);
        return restTemplate.postForObject("http://flaskservice/sentiment", s, String.class);
        }

        @GetMapping("testGet")
        public String getTest(){
        return "test";
        }

    @RequestMapping(value = "/getSentimentList", method = RequestMethod.GET)
    public List<Sentiment> getSentiments() throws JsonProcessingException {
        List<Sentiment> sentiments;
        sentiments=reportService.generateSentimentReport();
        System.out.println(sentiments.size());
        return sentiments;
    }

    @RequestMapping(value = "/getCommonWords",method = RequestMethod.GET)
    public List<CommonWords> getCommonWords() throws JsonProcessingException {
        List<CommonWords> commonWords;
        commonWords=reportService.generateCommonWords();
        return commonWords;
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        InputStreamReader isReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        //Creating a BufferedReader object
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = reader.readLine()) != null) {
            sb.append(str);
            sb.append(System.getProperty("line.separator"));
        }

        return new ResponseEntity<>(sb, HttpStatus.OK);
    }



}

