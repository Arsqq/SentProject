package com.example.eurekaclient1.Repo;

import com.example.eurekaclient1.Model.SentimentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentimentReportRepo extends JpaRepository<SentimentReport,Long> {
}
