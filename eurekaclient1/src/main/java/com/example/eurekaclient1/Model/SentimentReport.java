package com.example.eurekaclient1.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SentimentReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User moderator;

    private String fileFormat;
    private Double amountOfData;
    private Double negativePercent;
    private Double positivePercent;
    private LocalDateTime runAt;


}
