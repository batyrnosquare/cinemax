package dev.cinemax.cinemax.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.cinemax.cinemax.entity.Movies;
import dev.cinemax.cinemax.entity.Role;
import dev.cinemax.cinemax.entity.User;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String username;
    private String email;
    private String password;
    private Role roles;
    private List<String> watchlist;
    private User user;

    public ReqRes(){

    }
    public ReqRes(String message){
        this.message = message;
    }
}
