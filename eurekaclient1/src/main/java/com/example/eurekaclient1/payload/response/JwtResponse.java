package com.example.eurekaclient1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private Date dateOfLastLogOn;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles,Date dateOfLastLogOn) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.dateOfLastLogOn=dateOfLastLogOn;
	}


}
