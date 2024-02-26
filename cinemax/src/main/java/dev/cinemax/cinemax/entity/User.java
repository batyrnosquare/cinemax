package dev.cinemax.cinemax.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;

@Data
@Entity
@Document(collection = "users")
public class User{

    @Id
    private ObjectId id;
    private String username;
    private String email;
    private String password;
    private Role roles;

}