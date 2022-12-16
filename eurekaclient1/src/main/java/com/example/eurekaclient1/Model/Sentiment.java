package com.example.eurekaclient1.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

public class Sentiment {

    private SentimentPart sentiment;
    private List<EntityPart> entities;

    @Override
    public String toString() {
        return "Sentiment{" +
                "sentiment=" + sentiment +
                ", entities=" + entities ;

    }

    public Sentiment(){

    }

}
