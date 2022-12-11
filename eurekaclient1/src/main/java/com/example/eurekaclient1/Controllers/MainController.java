package com.example.eurekaclient1.Controllers;

import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/allUsers")
public class MainController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/listUsers")
    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
