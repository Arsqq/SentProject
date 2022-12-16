package com.example.eurekaclient1.Model;

import lombok.Data;

@Data
public class SentimentPart {

    private double polarity;
    private String sentiment;
    private String textContent;

    @Override
    public String toString() {
        return "polarity=" + polarity +
                ", sentiment='" + sentiment + '\'' +
                ", textContent='" + textContent + '\'' +
                '}';
    }
}
