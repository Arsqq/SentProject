package com.example.eurekaclient1.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="usr")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateOfLastLogOn;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String mail;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();




    public User() {
    }

    public User(String username, String mail, String password) {
        this.username = username;
        this.mail =  mail;
        this.password = password;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "moderator",cascade = CascadeType.ALL,fetch =FetchType.LAZY)
    private Set<SentimentReport> performedAnalyzes;
}
