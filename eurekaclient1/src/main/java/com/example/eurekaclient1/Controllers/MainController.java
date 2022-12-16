package com.example.eurekaclient1.Controllers;

import com.example.eurekaclient1.Model.SentimentReport;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Repo.SentimentReportRepo;
import com.example.eurekaclient1.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/allUsers")
public class MainController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SentimentReportRepo sentimentReportRepo;

    @GetMapping("/listUsers")
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/listReports")
    public List<SentimentReport> listReports(){
        return sentimentReportRepo.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
    }


}
