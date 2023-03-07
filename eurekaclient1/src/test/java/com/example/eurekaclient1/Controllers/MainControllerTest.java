package com.example.eurekaclient1.Controllers;

import com.example.eurekaclient1.Config.WebSecurityConfig;
import com.example.eurekaclient1.Controllers.AuthController;
import com.example.eurekaclient1.Controllers.MainController;
import com.example.eurekaclient1.Model.SentimentReport;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Repo.SentimentReportRepo;
import com.example.eurekaclient1.Repo.UserRepository;
import com.example.eurekaclient1.Service.UserDetailsServiceImpl;
import com.example.eurekaclient1.jwt.AuthEntryPointJwt;
import com.example.eurekaclient1.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserRepository userRepository;
    @MockBean
    private SentimentReportRepo sentimentReportRepo;
    @InjectMocks
    private MainController mainController;

    @Test
    @WithMockUser(username = "Ars",password = "123456",roles = "ADMIN")
    @DisplayName("getAllUsers")
    void getAllUsers_RoleAdmin() throws Exception {
        User user=new User("username","mail","password");
        user.setId(1L);
        List<User> users = List.of(user);
        when(userRepository.findAll()).thenReturn(users);
        mockMvc.perform(
                get("/api/allUsers/listUsers")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].username").value("username"));
    }

    @Test
    @WithMockUser(username = "Ars",password = "123456",roles = "ADMIN")
    @DisplayName("getAllReports")
    void getAllReports_RoleAdmin() throws Exception {
        SentimentReport sentimentReport=new SentimentReport();
        sentimentReport.setId(1L);
        sentimentReport.setAmountOfData(1000.5);
        List<SentimentReport> reports = List.of(sentimentReport);
        when(sentimentReportRepo.findAll()).thenReturn(reports);
        mockMvc.perform(
                        get("/api/allUsers/listReports")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].amountOfData").value("1000.5"));
    }

    @Test
    @WithMockUser(username = "Ars",password = "123456",roles = "ADMIN")
    @DisplayName("DeleteUsers")
    void deleteUser() throws Exception{
        User user=new User("username","mail","password");
        user.setId(1L);
        doNothing().when(userRepository).delete(user);
        mockMvc.perform(delete("/api/allUsers/delete/{id}",1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk());

    }

}
