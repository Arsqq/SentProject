package com.example.eurekaclient1.Controllers;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class SentimentController {

    @Autowired
    private RestTemplate restTemplate;

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
}

