package dev.cinemax.cinemax.service;

import dev.cinemax.cinemax.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface IUserService {
    Users registerUser(Users user);
    List<Users> getUsers();
    void deleteUser(String email);
    Users getUser(String email);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}