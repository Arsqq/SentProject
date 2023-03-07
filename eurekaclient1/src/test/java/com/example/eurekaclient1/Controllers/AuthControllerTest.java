package com.example.eurekaclient1.Controllers;

import com.example.eurekaclient1.Config.WebSecurityConfig;
import com.example.eurekaclient1.Controllers.AuthController;
import com.example.eurekaclient1.Model.ERole;
import com.example.eurekaclient1.Model.User;
import com.example.eurekaclient1.Service.UserDetailsImpl;
import com.example.eurekaclient1.Service.UserDetailsServiceImpl;
import com.example.eurekaclient1.jwt.AuthTokenFilter;
import com.example.eurekaclient1.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String TOKEN = "This is a token";

    @MockBean
    private JwtUtils JwtUtils;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AuthController authController;
    /*
    @Test
    @DisplayName("Login")
    public void login() throws Exception {
        String username="username";
        String password="password";
        String mail="mail@gmail.com";
        Date date=new Date();
        List<GrantedAuthority> gr=new ArrayList<>();
        UserDetailsImpl userDetailsIml=new UserDetailsImpl(1L,date,username,mail,password,gr) ;
        ResultActions resultActions = mockMvc.perform(
                post("/api/auth/signin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(userDetailsIml)));
        resultActions.andDo(print());
        resultActions.andExpect(status().isOk());
    }
*/
}
