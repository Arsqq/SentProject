package com.example.eurekaclient1.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Sentiment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String verdict;
    private Double polarity;

    private String entityType;
    private String entityName;
}
